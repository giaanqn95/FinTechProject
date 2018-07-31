package com.example.dell.fintechproject.module.base_activity;

import android.content.Context;

public interface BaseView {

    void runActivity(Context mContext, int id);

    void addView();

    boolean isNetworkConnect();

}
