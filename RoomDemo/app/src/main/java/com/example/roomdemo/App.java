package com.example.roomdemo;

import android.app.Application;

/**
 * TODO
 * shige chen on 2020/2/26
 * shigechen@globalsources.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppDataBase.initDataBase(this);
    }
}
