package com.example.dell.fintechproject;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.dell.fintechproject.AppConstants.FOUR;
import static com.example.dell.fintechproject.MainActivity.saveActivityList;

public class FourActivity extends BaseActivity {

    JSONObject jsonObjectFour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        jsonObjectFour = new JSONObject();
        try {
            jsonObjectFour.put("activity", FOUR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(jsonObjectFour);
    }

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
