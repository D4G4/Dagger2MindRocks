package com.d4enterprises.daggermindrocks.di.module;

import android.app.Activity;
import android.content.Context;

import com.d4enterprises.daggermindrocks.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dennis.
 */

@Module
public class MainActivityModule {

private Activity mActivity;

public MainActivityModule(Activity activity) {
    mActivity = activity;
}

@Provides
@ActivityContext
Context provideContext() {
    return mActivity;
}

@Provides
Activity provideActivity() {
    return mActivity;
}


}
