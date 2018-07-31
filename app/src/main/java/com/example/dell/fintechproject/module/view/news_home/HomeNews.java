package com.example.dell.fintechproject.module.view.news_home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.News;
import com.example.dell.fintechproject.module.base_activity.BaseActivity;
import com.example.dell.fintechproject.module.view.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.dell.fintechproject.AppConstants.CURRENCY;
import static com.example.dell.fintechproject.AppConstants.MAP;
import static com.example.dell.fintechproject.module.view.MainActivity.saveActivityList;


public class HomeNews extends BaseActivity implements NewsHomeGeneral.NewsHomeView {

    TextView mButtonMap, mButtonMoney;
    int i = 0;
    Handler handler = new Handler();
    TextView mTextView;
    private int min = 0, max = 3;
    Random random = new Random();
    int randomNum;
    RecyclerView mRecyclerView;
    List<News> mNewsList = new ArrayList<>();
    NewsAdapter mNewsAdapter;
    NewsHomePresenterImpl mNewsHomePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        run();
////        MyTask myTask = new MyTask();
//        myTask.execute(31321);
        initPresenter();
        init();
    }

    protected void initPresenter() {
        mNewsHomePresenter = new NewsHomePresenterImpl(this);
        mNewsHomePresenter.getNews();
    }

    public void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_news);
        mNewsAdapter = new NewsAdapter(this, mNewsList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeNews.this));
        mRecyclerView.setAdapter(mNewsAdapter);
//        mNewsAdapter.notifyDataSetChanged();
        mButtonMap = (TextView) findViewById(R.id.btn_map);
        mButtonMoney = (TextView) findViewById(R.id.btn_money);
//        mTextView = (TextView) findViewById(R.id.tv_test);
        mButtonMap.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, MAP, true));
        mButtonMoney.setOnClickListener(view -> MainActivity.getInstance().runActivity(this, CURRENCY, true));
    }

    @Override
    public void getDataNews(List<News> news) {
        if (news.size() > 0) {
            mNewsList.addAll(news);
            mNewsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        if (saveActivityList.size() == 1) {
        }
    }

//    public void run() {
//
//        new Thread(() -> {
//            while (i < 21321) {
//                handler.post(() -> {
//                    random.nextBoolean();
//                    randomNum = random.nextInt((max - min) + 1) + min;
//                    Log.d("AAAAA", String.valueOf(randomNum));
//                    mTextView.setText(String.valueOf(randomNum));
//                });
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    private class MyTask extends AsyncTask<Integer, Integer, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(Integer... integers) {
//            new Thread(() -> {
//                while (i < 31321) {
//                    handler.postDelayed(() -> {
//                        i += 1;
//                        Log.d("AAAAA", String.valueOf(i));
//                        mTextView.setText(String.valueOf(i));
//                    }, 5000);
//                    try {
//                        Thread.sleep(0, 1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            mTextView.setText(s);
//
//        }
//    }

}
