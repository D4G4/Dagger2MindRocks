package com.d4enterprises.daggermindrocks.data;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.d4enterprises.daggermindrocks.data.model.User;
import com.d4enterprises.daggermindrocks.di.ApplicationContext;

/**
 * Created by Dennis.
 */

/**
 * This class expresses the dependencies of Application's (tells us that it needs)
 * {@link Context},
 * {@link DatabaseHelper} and
 * {@link SharedPrefsHelper}
 * in it's constructor. It provides all the apis to access the data in the application.
 */
@Singleton
public class DataManager {

private Context mContext;
private DatabaseHelper mDbHelper;
private SharedPrefsHelper mSharedPreferencesHelper;

//By defining the required dependencies in the constructor, dagger will automatically provide this class with the stuff needed.
@Inject
public DataManager(@ApplicationContext Context context,
                   DatabaseHelper databaseHelper,
                   SharedPrefsHelper sharedPrefsHelper) {
    mContext = context;
    mDbHelper = databaseHelper;
    mSharedPreferencesHelper = sharedPrefsHelper;
}

public void saveAccessToken(String accessToken) {
    mSharedPreferencesHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, accessToken);
}

public String getAccessToken() {
    return mSharedPreferencesHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN, null);
}

public Long createUser(User user) throws SQLiteException {
    return mDbHelper.insertUser(user);
}

public User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
    return mDbHelper.getUser(userId);
}
}
