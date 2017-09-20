package io.natalietay.sparrow;

import android.app.Application;

import io.natalietay.sparrow_service.SparrowServiceModule;

/**
 * Created by Natalie on 19/9/17.
 */

public class SparrowApplication extends Application {
    AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // the DaggerAppComponent is generated by Dagger, using our AppComponent class
        appComponent = DaggerAppComponent
                .builder()

                // for every module that the AppComponent has, we need to instantiate them
                .sparrowServiceModule(new SparrowServiceModule())
                .sparrowApplicationModule(new SparrowApplicationModule())
                .build();
    }
}