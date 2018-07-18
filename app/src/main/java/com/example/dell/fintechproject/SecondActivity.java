package com.example.dell.fintechproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.widget.Button;

import static com.example.dell.fintechproject.AppConstants.SECOND;
import static com.example.dell.fintechproject.AppConstants.THIRD;
import static com.example.dell.fintechproject.MainActivity.saveActivityList;


public class SecondActivity extends BaseActivity {

    Button mButton, mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    public void init() {
        mButton = (Button) findViewById(R.id.btn_test);
        mButton2 = (Button) findViewById(R.id.btn_test2);
        mButton2.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, THIRD));
        mButton.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, SECOND));
    }

    @Override
    public void onBackPressed() {
        if (saveActivityList.size() == 1) {
//            saveActivityList.remove(saveActivityList.size() - 1);
//            finish();
        }
    }
}
