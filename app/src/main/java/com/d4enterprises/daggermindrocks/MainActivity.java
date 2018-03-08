package com.d4enterprises.daggermindrocks;

import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.d4enterprises.daggermindrocks.data.DataManager;
import com.d4enterprises.daggermindrocks.data.model.User;

import com.d4enterprises.daggermindrocks.di.component.DaggerMainActivityComponent;
import com.d4enterprises.daggermindrocks.di.component.MainActivityComponent;
import com.d4enterprises.daggermindrocks.di.module.MainActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

@Inject
DataManager mDataManager;

private MainActivityComponent activityComponent;

private TextView mTvUserInfo;
private TextView mTvAccessToken;


public MainActivityComponent getActivityComponent() {
    if (activityComponent == null) {
        activityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .demoApplicationComponent(DemoApplication.getDemoApplication(this).getDemoApplicationComponent())
                .build();
    }
    return activityComponent;
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);    //for DataManger

    mTvUserInfo = findViewById(R.id.tv_user_info);
    mTvAccessToken = findViewById(R.id.tv_access_token);
}

@Override
protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    createUser();
    getUser();

    mDataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543");

    String token = mDataManager.getAccessToken();

    if (token != null)
        mTvAccessToken.setText(token);

}

private void getUser() {
    try {
        User user = mDataManager.getUser(1L);
        mTvUserInfo.setText(user.toString());
    } catch (NullPointerException | Resources.NotFoundException e) {
        e.printStackTrace();
    }

}

private void createUser() {
    try {
        mDataManager.createUser(new User("Daksh",
                "774 Sec 40 Gurgaon, Harayana, India"));
    } catch (SQLiteException e) {
        e.printStackTrace();
    }
}
}
