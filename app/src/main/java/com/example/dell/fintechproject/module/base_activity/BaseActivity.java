package com.example.dell.fintechproject.module.base_activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.fintechproject.utils.InternetConnection;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initPresenter();
//        clickView();
    }

    @Override
    public void runActivity(Context mContext, int id) {

    }

    @Override
    public void addView() {

    }

    @Override
    public boolean isNetworkConnect() {
        if (InternetConnection.getInstance().isOnline(this)) {
            return true;
        }
        return false;
    }



//    protected abstract void initView();
//
//    protected abstract void onClick();


}
