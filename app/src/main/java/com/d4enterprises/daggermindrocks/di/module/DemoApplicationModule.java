package com.d4enterprises.daggermindrocks.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

import com.d4enterprises.daggermindrocks.di.ApplicationContext;
import com.d4enterprises.daggermindrocks.di.DatabaseInfo;

/**
 * Created by Dennis.
 */

/**
 * DEPENDENCY PROVIDER
 * It will provide all the necessary things needed by
 * {@link com.d4enterprises.daggermindrocks.DemoApplication}
 */
@Module
public class DemoApplicationModule {

private final Application mApplication;

public DemoApplicationModule(Application app) {
    mApplication = app;
}

@Provides
@ApplicationContext
//explicitly defining the type of context
Context provideApplicationContext() {
    return mApplication;
}

@Provides
Application provideApplication() {
    return mApplication;
}

@Provides
@DatabaseInfo
String provideDatabaseName() {
    return "demo-dagger.db";
}

@Provides
@DatabaseInfo
Integer provideDatabaseVersion() {
    return 1;
}

@Provides
SharedPreferences provideSharedPrefs() {
    return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
}
}
