package com.webdroidteam.teste_layout_1;


import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Leonardo on 28/05/2016.
 */
public class MGApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this, true);
    }
}
