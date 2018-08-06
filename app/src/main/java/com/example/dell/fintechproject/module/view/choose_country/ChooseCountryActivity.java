package com.example.dell.fintechproject.module.view.choose_country;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.ListRate;
import com.example.dell.fintechproject.module.base_activity.BaseActivity;
import com.example.dell.fintechproject.module.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.fintechproject.AppConstants.CHOOSE;
import static com.example.dell.fintechproject.module.view.MainActivity.myListRateSelect;
import static com.example.dell.fintechproject.module.view.MainActivity.open;
import static com.example.dell.fintechproject.module.view.MainActivity.saveActivityList;

public class ChooseCountryActivity extends BaseActivity {

    JSONObject mJsonObjectChoose = null;
    ImageView mImageViewBack;
    TextView mTextViewFavorite;
    RecyclerView mRecyclerView;
    ChooseCountryAdapter mChooseCountryAdapter;
    List<ListRate> mListRates = new ArrayList<>();
    int countChoose;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);
        EventBus.getDefault().register(this);
        if (open) {
            save();
        }
        initView();
        clickView();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(List<ListRate> rates) {
        mListRates.addAll(rates);
    }

    public void initView() {
        mImageViewBack = (ImageView) findViewById(R.id.tb_choose);
        mTextViewFavorite = (TextView) findViewById(R.id.tv_choose_favorite);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_choose);
        mChooseCountryAdapter = new ChooseCountryAdapter(this, mListRates);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mChooseCountryAdapter);
        countChoose = myListRateSelect.size();
        mTextViewFavorite.setText(String.format(getResources().getString(R.string.favorite), String.valueOf(countChoose)));
    }

    public void clickView() {
        mImageViewBack.setOnClickListener(view -> MainActivity.getInstance().backActivity(this));
        mChooseCountryAdapter.setOnCheckListener(new ChooseCountryAdapter.OnCheckBoxListener() {
            @Override
            public void onItemAddClickListener(int position) {
                if (myListRateSelect.size() < 4 && mListRates.get(position).isFavorite() == false) {
                    myListRateSelect.add(mListRates.get(position));
//                    if (myListRateSelect.get(position).getCurrencyCode().equals(mListRates.get(position).getCurrencyCode())) {
//                        myListRateSelect.get(position).setFavorite(true);
//                    }
                    countChoose = myListRateSelect.size();
                    mTextViewFavorite.setText(String.format(getResources().getString(R.string.favorite), String.valueOf(countChoose)));
                } else {
                }
            }

            @Override
            public void onItemRemoveClickListener(int position) {
                if (myListRateSelect.size() > 1) {
                    myListRateSelect.remove(mListRates.get(position));
                    countChoose = myListRateSelect.size();
//                    if (myListRateSelect.get(position).getCurrencyCode().equals(mListRates.get(position).getCurrencyCode())) {
//                        myListRateSelect.get(position).setFavorite(false);
//                    }
                    mTextViewFavorite.setText(String.format(getResources().getString(R.string.favorite), String.valueOf(countChoose)));
                } else {
                }
            }
        });
    }

    public void save() {
        mJsonObjectChoose = new JSONObject();
        try {
            mJsonObjectChoose.put("activity", CHOOSE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(mJsonObjectChoose);
    }

    @Override
    public void onBackPressed() {
        MainActivity.getInstance().backActivity(this);
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        MainActivity.getInstance().backActivity(this);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        MainActivity.getInstance().backActivity(this);
    }
}
