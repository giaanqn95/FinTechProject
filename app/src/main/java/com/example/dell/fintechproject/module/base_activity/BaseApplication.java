package com.example.dell.fintechproject.module.base_activity;

import android.app.Activity;
import android.app.Application;

import java.util.Locale;

public class BaseApplication extends Application {
    private static Application instance = new BaseApplication();

    public static Application getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String locale= Locale.getDefault().getLanguage();
    }

    private android.app.Activity mCurrentActivity = null;
    public android.app.Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

}