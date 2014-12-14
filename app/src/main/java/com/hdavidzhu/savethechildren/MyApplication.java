package com.hdavidzhu.savethechildren;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleySingleton.init(getApplicationContext());
    }
}