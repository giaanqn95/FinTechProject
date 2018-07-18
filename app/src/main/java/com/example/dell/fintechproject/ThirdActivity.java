package com.example.dell.fintechproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.dell.fintechproject.AppConstants.FOUR;
import static com.example.dell.fintechproject.AppConstants.THIRD;
import static com.example.dell.fintechproject.MainActivity.activityList;
import static com.example.dell.fintechproject.MainActivity.jsonObjectSecond;
import static com.example.dell.fintechproject.MainActivity.saveActivityList;

public class ThirdActivity extends BaseActivity {

    EditText mEditText, mEditText2;
    String test1, test2;
    TextView mTextView1, mTextView2;
    JSONObject jsonObjectData = null, jsonObjectThird;
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mButton = (Button) findViewById(R.id.btn_click);
        mEditText = (EditText) findViewById(R.id.et_test);
        mEditText2 = (EditText) findViewById(R.id.et_test2);
        mButton.setOnClickListener(view -> getData());
        try {
            mEditText.setText(saveActivityList.get(saveActivityList.size() - 1).getString("test1"));
            mEditText2.setText(saveActivityList.get(saveActivityList.size() - 1).getString("test2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        test1 = mEditText.getText().toString();
        test2 = mEditText2.getText().toString();
        jsonObjectData = new JSONObject();
        try {
            jsonObjectData.put("activity", THIRD);
            jsonObjectData.put("test1", test1);
            jsonObjectData.put("test2", test2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MainActivity.getInstance().runActivity(this, FOUR);
        saveActivityList.add(jsonObjectData);
        Log.d("AAAAA", String.valueOf(jsonObjectSecond + "\n" + jsonObjectData));

    }

//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void onClick() {
//
//    }


//    @Override
//    public void runActivity(Context mContext, int id) {
//
//    }
//
//    @Override
//    public void addView() {
//
//    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (saveActivityList.size() > 1) {
            int id = 0;
            try {
                id = Integer.parseInt(saveActivityList.get(saveActivityList.size() - 2).getString("activity"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
            MainActivity.getInstance().runActivity(this, id);
//            finish();
        }
    }
}
