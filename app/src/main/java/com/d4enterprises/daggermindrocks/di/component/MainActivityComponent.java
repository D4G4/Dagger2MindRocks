package com.d4enterprises.daggermindrocks.di.component;

import com.d4enterprises.daggermindrocks.MainActivity;
import com.d4enterprises.daggermindrocks.di.PerActivity;
import com.d4enterprises.daggermindrocks.di.module.MainActivityModule;


import dagger.Component;

/**
 * Created by Dennis.
 */

/**
 * We have used the custom {@link javax.inject.Scope} i.e. {@link PerActivity}
 * to tell Dagger that the Context and Activity provided by {@link MainActivityModule}
 * will be instantiated each thime the Activity is created.
 */
@PerActivity
@Component(dependencies = DemoApplicationComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

void inject(MainActivity activity);
}
