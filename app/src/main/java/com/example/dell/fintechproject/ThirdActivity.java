package com.example.dell.fintechproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.dell.fintechproject.AppConstants.FOUR;
import static com.example.dell.fintechproject.AppConstants.SECOND;
import static com.example.dell.fintechproject.AppConstants.THIRD;
import static com.example.dell.fintechproject.MainActivity.jsonObjectSecond;
import static com.example.dell.fintechproject.MainActivity.saveActivity;
import static com.example.dell.fintechproject.MainActivity.saveActivityList;

public class ThirdActivity extends BaseActivity {

    EditText mEditText, mEditText2;
    String test1, test2;
    JSONObject jsonObjectData = null, jsonObject = null;
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
        } catch (Exception e) {
            Log.d( "AAAAA", e.getMessage());
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
        saveActivity.put(THIRD, jsonObjectData);
        saveActivityList.add(jsonObjectData);
        Log.d("AAAAA", String.valueOf(jsonObjectSecond + "\n" + jsonObjectData));
    }

    @Override
    public void onBackPressed() {
//        if (saveActivityList.size() > 1) {
//            int id = 0;
//            try {
//                id = Integer.parseInt(saveActivityList.get(saveActivityList.size() - 2).getString("activity"));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            MainActivity.getInstance().runActivity(this, id);
//        }
        try {
        jsonObject = new JSONObject();
        jsonObject = saveActivity.get(SECOND);
        int id = 0;

            id = Integer.parseInt(jsonObject.getString("activity"));

        MainActivity.getInstance().runActivity(this, id);
        saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
        } catch (Exception e) {
            Log.d( "AAAAA", e.getMessage());
        }
    }
}
