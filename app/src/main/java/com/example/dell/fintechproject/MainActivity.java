package com.example.dell.fintechproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.dell.fintechproject.AppConstants.SECOND;

public class MainActivity extends BaseActivity {

    private static MainActivity instance;
    static HashMap<Integer, Class> activityList = new HashMap<>();
    static List<JSONObject> saveActivityList = new ArrayList<>();
    public Class idActivity;
    static JSONObject jsonObjectSecond = null;


    public static MainActivity getInstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        addView();
        runActivity(this, SECOND);
        saveHistory(SECOND);
    }


    public void runActivity(Context mContext, int id) {
        idActivity = activityList.get(id);
        Intent intent = new Intent(mContext, idActivity);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void saveHistory(int id) {
        jsonObjectSecond = new JSONObject();
        try {
            jsonObjectSecond.put("activity", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(jsonObjectSecond);
    }

    public void addView() {
        for (NameClass nameClass : NameClass.values()) {
            activityList.put(nameClass.getKey(), nameClass.getName());
        }
    }
}