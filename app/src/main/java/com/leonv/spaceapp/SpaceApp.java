package com.leonv.spaceapp;

import android.app.Application;

import com.leonv.spaceapp.API.SpaceXApiManager;

public class SpaceApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SpaceXApiManager getApiManager(){
        return SpaceXApiManager.getInstance(this);
    }
}
