package com.example.dell.fintechproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.example.dell.fintechproject.AppConstants.SECOND;

public class MainActivity extends BaseActivity {

    private static MainActivity instance;
    static HashMap<Integer, Class> activityList = new HashMap<>();
    static HashMap<Integer, JSONObject> saveActivity = new HashMap<>();
    static List<JSONObject> saveActivityList = new ArrayList<>();
    public Class idActivity;
    static JSONObject jsonObjectSecond = null;
    private Handler handler;
    private int min = 1, max= 3;
    private int i = 0;
    Random random = new Random();
    int randomNum;

    public static MainActivity getInstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        handler = new Handler();
        addView();
//        run();
        runActivity(this, SECOND);
        saveHistory(SECOND);

    }


    public void runActivity(Context mContext, int id) {
        idActivity = activityList.get(id);
        Intent intent = new Intent(mContext, idActivity);
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
        saveActivity.put(id, jsonObjectSecond);
    }

    public void addView() {
        for (NameClass nameClass : NameClass.values()) {
            activityList.put(nameClass.getKey(), nameClass.getName());
        }
    }
    public void run() {
        randomNum = random.nextInt((max - min) + 1) + min;
        new Thread(() -> {
            while (i < 3) {
                handler.post(() -> {
                    i += 1;
                    random.nextBoolean();
                    randomNum = random.nextInt((max - min) + 1) + min;
                    Log.d("AAAAA", String.valueOf(randomNum));
                    runActivity(this, randomNum);
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}