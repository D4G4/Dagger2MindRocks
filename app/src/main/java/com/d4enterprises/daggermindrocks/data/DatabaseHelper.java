package com.d4enterprises.daggermindrocks.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.d4enterprises.daggermindrocks.data.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.d4enterprises.daggermindrocks.di.ApplicationContext;
import com.d4enterprises.daggermindrocks.di.DatabaseInfo;

/**
 * Created by dakshgargas
 */

@Singleton
public class DatabaseHelper extends SQLiteOpenHelper {

//Todo: Was public
//USER TABLE
private static final String TABLE_NAME = "users";
private static final String COLUMN_USER_ID = "id";
private static final String COLUMN_USER_NAME = "usr_name";
private static final String COLUMN_USER_ADDRESS = "usr_add";
private static final String COLUMN_USER_CREATED_AT = "created_at";
private static final String COLUMN_USER_UPDATED_AT = "updated_at";

@Inject
public DatabaseHelper(@ApplicationContext Context context,
                      @DatabaseInfo String name,
                      @DatabaseInfo int version) {
    super(context, name, null, version);
}

@Override
public void onCreate(SQLiteDatabase db) {
    tableCreateStatement(db);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
}

private void tableCreateStatement(SQLiteDatabase db) {
    try {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "
                        + TABLE_NAME + "("
                        + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_USER_NAME + " VARCHAR(20), "
                        + COLUMN_USER_ADDRESS + " VARCHAR(50), "
                        + COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                        + COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
        );
    } catch (SQLiteException ex) {
        ex.printStackTrace();
    }
}

protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
    Cursor cursor = null;
    try {
        SQLiteDatabase database = this.getReadableDatabase();
//        cursor = database.rawQuery(
//                "SELECT * FROM " + TABLE_NAME
//                        + " WHERE "
//                        + COLUMN_USER_ID
//                        + "=?", new String[]{String.valueOf(userId)});

        cursor = database.query(TABLE_NAME,
                new String[]{COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_ADDRESS, COLUMN_USER_CREATED_AT, COLUMN_USER_UPDATED_AT},
                COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            User user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADDRESS)));
            user.setCreatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CREATED_AT)));
            user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_UPDATED_AT)));
            return user;
        } else {
            throw new Resources.NotFoundException("User with id " + userId + " does not exist");
        }
    } catch (NullPointerException nullPointerException) {
        nullPointerException.printStackTrace();
        throw nullPointerException;
    } finally {
        if (cursor != null)
            cursor.close();
    }
}

protected Long insertUser(User user) {
    try {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, user.getName());
        contentValues.put(COLUMN_USER_ADDRESS, user.getAddress());
        return database.insert(TABLE_NAME, null, contentValues);
    } catch (SQLiteException e) {
        e.printStackTrace();
        throw e;
    }
}

private String getCurrentTimeStamp() {
    return String.valueOf(System.currentTimeMillis() / 1000);
}
}
