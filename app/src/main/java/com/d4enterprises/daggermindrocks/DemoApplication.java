package com.d4enterprises.daggermindrocks;

import android.app.Application;
import android.content.Context;

import com.d4enterprises.daggermindrocks.data.DataManager;
import com.d4enterprises.daggermindrocks.di.component.DaggerDemoApplicationComponent;
import com.d4enterprises.daggermindrocks.di.component.DemoApplicationComponent;
import com.d4enterprises.daggermindrocks.di.module.DemoApplicationModule;

import javax.inject.Inject;


/**
 * Created by Dennis.
 */

/**
 * DEPENDENCY CONSUMER
 */
public class DemoApplication extends Application {

protected DemoApplicationComponent applicationComponent;

@Inject
DataManager dataManager;

public static DemoApplication getDemoApplication(Context context) {
    return (DemoApplication) context.getApplicationContext();
}

@Override
public void onCreate() {
    super.onCreate();
    //Building the Component(Link between DemoApplication and DemoApplicationModule)
    applicationComponent = DaggerDemoApplicationComponent
            .builder()
            .demoApplicationModule(new DemoApplicationModule(this))
            .build();

    //this is done to use it for providing DataManager components from DemoApplicationModule
    applicationComponent.inject(this);
}

public DemoApplicationComponent getDemoApplicationComponent() {
    return applicationComponent;
}
}

