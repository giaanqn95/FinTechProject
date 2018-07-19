package com.example.dell.fintechproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.dell.fintechproject.AppConstants.FOUR;
import static com.example.dell.fintechproject.AppConstants.SECOND;
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
        //TODO: Cách hanldle lỗi nếu crash trả về activity đầu tiên. Xem kĩ throw, nên để Exception
        try {
            if (saveActivityList.size() > 1) {
                int id = 0;

                id = Integer.parseInt(saveActivityList.get(saveActivityList.size() - 2).getString("activity"));

                saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
                MainActivity.getInstance().runActivity(this, id);
//            finish();

            }
        } catch (Exception e) {
            Log.d("Andeptrai", "Unknown error cause by " + e.getMessage());
            MainActivity.getInstance().runActivity(this, SECOND);
        }
    }
}
