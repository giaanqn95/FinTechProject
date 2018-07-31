package com.example.dell.fintechproject.module.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dell.fintechproject.NameClass;
import com.example.dell.fintechproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.example.dell.fintechproject.AppConstants.HOME;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    static HashMap<Integer, Class> activityList = new HashMap<>();
    public static HashMap<Integer, JSONObject> saveActivity = new HashMap<>();
    public static List<JSONObject> saveActivityList = new ArrayList<>();
    public static Boolean open;
    public Class idActivity;
    static JSONObject jsonObjectSecond = null;
    private Handler handler;
    private int min = 1, max = 3;
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
        runActivity(this, HOME, true);
        saveHistory(HOME);

    }


    public void runActivity(Context mContext, int id, Boolean boo) {
        idActivity = activityList.get(id);
        Intent intent = new Intent(mContext, idActivity);
        startActivity(intent);
        open = boo;
        finish();
        Log.d("TAG123", String.valueOf(saveActivityList.size()));
    }

    public void saveHistory(int id) {
        jsonObjectSecond = new JSONObject();
        try {
            jsonObjectSecond.put("activity", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(jsonObjectSecond);
//        saveActivity.put(id, jsonObjectSecond);
        Log.d("TAG123", String.valueOf(saveActivityList.size()));
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
                    runActivity(this, randomNum, true);
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void backActivity(Context mContext) {
        if (saveActivityList.size() > 1) {
            int id = 0;
            try {
                id = Integer.parseInt(saveActivityList.get(saveActivityList.size() - 2).getString("activity"));
                saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            open = false;
            MainActivity.getInstance().runActivity(mContext, id, false);
        }
    }
}