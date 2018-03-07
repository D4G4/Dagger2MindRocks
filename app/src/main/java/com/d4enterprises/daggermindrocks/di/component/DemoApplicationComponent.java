package com.d4enterprises.daggermindrocks.di.component;

import android.content.Context;

import com.d4enterprises.daggermindrocks.DemoApplication;
import com.d4enterprises.daggermindrocks.data.DataManager;
import com.d4enterprises.daggermindrocks.data.DatabaseHelper;
import com.d4enterprises.daggermindrocks.data.SharedPrefsHelper;
import com.d4enterprises.daggermindrocks.di.ApplicationContext;
import com.d4enterprises.daggermindrocks.di.module.DemoApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by Dennis.
 */

/**
 * This component will be used by Dagger2 and this interface will link
 * com.d4enterprises.daggermindrocks.DemoApplication.class
 * with
 * {@link DemoApplicationModule}
 */
@Singleton
@Component(modules = DemoApplicationModule.class)  //Stating which modules to connect with
public interface DemoApplicationComponent {

void inject(DemoApplication demoApplication);

@ApplicationContext
Context getContext();

ApplicationContext getApplication();

DataManager getDataManager();

SharedPrefsHelper getPreferenceHelper();

DatabaseHelper getDatabaseHelper();
}
