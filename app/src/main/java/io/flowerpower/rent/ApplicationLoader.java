package io.flowerpower.rent;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class ApplicationLoader extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
