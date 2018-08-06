package com.example.dell.fintechproject.module.view.currency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.ListRate;
import com.example.dell.fintechproject.module.base_activity.BaseActivity;
import com.example.dell.fintechproject.module.view.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.dell.fintechproject.AppConstants.CURRENCY;
import static com.example.dell.fintechproject.AppConstants.EXCHANGE;
import static com.example.dell.fintechproject.module.view.MainActivity.myListRateSelect;
import static com.example.dell.fintechproject.module.view.MainActivity.open;
import static com.example.dell.fintechproject.module.view.MainActivity.saveActivityList;

public class CurrencyActivity extends BaseActivity implements CurrencyGeneral.CurrencyView {

    JSONObject jsonObjectCurrency;
    ImageView mImageViewBack, mImageViewExchange;
    RecyclerView mRecyclerView;
    CurrencyPresenterImpl mCurrencyPresenter;
    CurrencyAdapter mCurrencyAdapter;
    List<ListRate> listRates = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        if (open) {
            save();
        }
        initPresenter();
        init();
        onClick();
    }

    public void initPresenter() {
        mCurrencyPresenter = new CurrencyPresenterImpl(this);
        mCurrencyPresenter.getCurrency();
    }

    public void init() {
        mImageViewBack = (ImageView) findViewById(R.id.tb_currency);
        mImageViewExchange = (ImageView) findViewById(R.id.currency_iv_exchange);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_currency);
        mCurrencyAdapter = new CurrencyAdapter(this, listRates);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mCurrencyAdapter);
    }

    public void onClick() {
        mImageViewBack.setOnClickListener(view -> MainActivity.getInstance().backActivity(this));
        mImageViewExchange.setOnClickListener(view -> {
            MainActivity.getInstance().runActivity(this, EXCHANGE, true);
            EventBus.getDefault().postSticky(listRates);
        });
    }

    public void save() {
        jsonObjectCurrency = new JSONObject();
        try {
            jsonObjectCurrency.put("activity", CURRENCY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(jsonObjectCurrency);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        //TODO: Cách hanldle lỗi nếu crash trả về activity đầu tiên. Xem kĩ throw, nên để Exception
//        try {
//            if (saveActivityList.size() > 1) {
//                int id = 0;
//                id = Integer.parseInt(saveActivityList.get(saveActivityList.size() - 2).getString("activity"));
//                saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
//                MainActivity.getInstance().runActivity(this, id);
//                finish();
//
//            }
//        } catch (Exception e) {
//            Log.d("Andeptrai", "Unknown error cause by " + e.getMessage());
//            MainActivity.getInstance().runActivity(this, HOME);
//        }
        MainActivity.getInstance().backActivity(this);
    }

    @Override
    public void getDataCurrency(List<ListRate> objectsList) {
        listRates.addAll(objectsList);
        mCurrencyAdapter.notifyDataSetChanged();
        if (myListRateSelect.size() == 0) {
            myListRateSelect.add(objectsList.get(0));
        }
    }
}
