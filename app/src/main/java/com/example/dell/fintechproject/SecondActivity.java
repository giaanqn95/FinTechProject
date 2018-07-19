package com.example.dell.fintechproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static com.example.dell.fintechproject.AppConstants.SECOND;
import static com.example.dell.fintechproject.AppConstants.THIRD;
import static com.example.dell.fintechproject.MainActivity.saveActivityList;


public class SecondActivity extends BaseActivity {

    Button mButton, mButton2;
    int i = 0;
    Handler handler = new Handler();
    TextView mTextView;
    private int min = 0, max= 3;
    Random random = new Random();
    int randomNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        run();
////        MyTask myTask = new MyTask();
//        myTask.execute(31321);
    }

    public void init() {
        mButton = (Button) findViewById(R.id.btn_test);
        mButton2 = (Button) findViewById(R.id.btn_test2);
        mTextView = (TextView) findViewById(R.id.tv_test);
        mButton2.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, THIRD));
        mButton.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, SECOND));
    }

    @Override
    public void onBackPressed() {
        if (saveActivityList.size() == 1) {
        }
    }

    public void run() {

        new Thread(() -> {
            while (i < 21321) {
                handler.post(() -> {
                    random.nextBoolean();
                    randomNum = random.nextInt((max - min) + 1) + min;
                    Log.d("AAAAA", String.valueOf(randomNum));
                    mTextView.setText(String.valueOf(randomNum));
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            new Thread(() -> {
                while (i < 31321) {
                    handler.postDelayed(() -> {
                        i += 1;
                        Log.d("AAAAA", String.valueOf(i));
                        mTextView.setText(String.valueOf(i));
                    }, 5000);
                    try {
                        Thread.sleep(0, 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            mTextView.setText(s);

        }
    }

}
