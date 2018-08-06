package com.example.dell.fintechproject.module.view.exchange;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.fintechproject.CurrencyIcon;
import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.ListRate;
import com.example.dell.fintechproject.module.base_activity.BaseActivity;
import com.example.dell.fintechproject.module.view.MainActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.dell.fintechproject.AppConstants.CHOOSE;
import static com.example.dell.fintechproject.AppConstants.EXCHANGE;
import static com.example.dell.fintechproject.module.view.MainActivity.myListRateSelect;
import static com.example.dell.fintechproject.module.view.MainActivity.open;
import static com.example.dell.fintechproject.module.view.MainActivity.saveActivityList;

public class ExchangeActivity extends BaseActivity {

    EditText mEditText;
    JSONObject jsonObjectExchange = null;
    TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6, mTextView7, mTextView8, mTextView9, mTextView0, mTextView000;
    ImageView mImageViewDeleted, mImageViewClear;
    RecyclerView mRecyclerViewExChange, mRecyclerViewChooseCountry;
    ExchangeAdapter mExchangeAdapter;
    ChooseAdapter mChooseAdapter;
    List<ListRate> mListRates = new ArrayList<>();
    ConstraintLayout mConstraintLayoutChoose, mConstraintLayoutKeyBroad;
    List<ListRate> mListRate = new ArrayList<>();
    ImageView mImageViewChoose;
    TextView mTextViewChoose;
    int oldPosition = 0;
    Double myMoney;
    HashMap<String, Integer> currencyIconList = new HashMap<String, Integer>();
    ImageView mImageViewBack, mImageViewSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        EventBus.getDefault().register(this);
        if (open) {
            save();
        }
        initKeyBroadAndView();
        clickView();
        clickKeyBroad();
        clickAdapter();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(List<ListRate> rates) {
        ListRate listRate = new ListRate("VNA", "VIETNAM DONG", "100000", "169374.07", "17119.12");
        mListRate.add(0, listRate);
//        mListRates.addAll(myListRateSelect);
        mListRate.addAll(rates);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    public void save() {
        jsonObjectExchange = new JSONObject();
        try {
            jsonObjectExchange.put("activity", EXCHANGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveActivityList.add(jsonObjectExchange);
    }

    public void initKeyBroadAndView() {
        mConstraintLayoutChoose = (ConstraintLayout) findViewById(R.id.cl_country);
        mConstraintLayoutKeyBroad = (ConstraintLayout) findViewById(R.id.cl_keybroad);
        mTextViewChoose = (TextView) findViewById(R.id.tv_choose_country);
        mImageViewChoose = (ImageView) findViewById(R.id.iv_choose_country);
        mEditText = (EditText) findViewById(R.id.et_money_exchange);
        mTextView0 = (TextView) findViewById(R.id.tv_number_0);
        mTextView1 = (TextView) findViewById(R.id.tv_number_1);
        mTextView2 = (TextView) findViewById(R.id.tv_number_2);
        mTextView3 = (TextView) findViewById(R.id.tv_number_3);
        mTextView4 = (TextView) findViewById(R.id.tv_number_4);
        mTextView5 = (TextView) findViewById(R.id.tv_number_5);
        mTextView6 = (TextView) findViewById(R.id.tv_number_6);
        mTextView7 = (TextView) findViewById(R.id.tv_number_7);
        mTextView8 = (TextView) findViewById(R.id.tv_number_8);
        mTextView9 = (TextView) findViewById(R.id.tv_number_9);
        mTextView000 = (TextView) findViewById(R.id.tv_number_000);
        mImageViewDeleted = (ImageView) findViewById(R.id.iv_delete);
        mImageViewClear = (ImageView) findViewById(R.id.iv_clear);
        mImageViewBack = (ImageView) findViewById(R.id.tb_exchange);
        mImageViewSetting = (ImageView) findViewById(R.id.exchange_iv_setting);
        mEditText.setFocusable(true);
//        mEditText.addTextChangedListener(new NumberTextWatcher(mEditText));
        mRecyclerViewExChange = (RecyclerView) findViewById(R.id.rv_exchange);
        mRecyclerViewChooseCountry = (RecyclerView) findViewById(R.id.rv_choose_country);
        Double test = Double.valueOf(0);

        mExchangeAdapter = new ExchangeAdapter(this, myListRateSelect, mListRate, test, oldPosition);
        mChooseAdapter = new ChooseAdapter(this, mListRate);
        mRecyclerViewExChange.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewExChange.setAdapter(mExchangeAdapter);
        mRecyclerViewChooseCountry.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewChooseCountry.setAdapter(mChooseAdapter);
        mExchangeAdapter.notifyDataSetChanged();
        mChooseAdapter.notifyDataSetChanged();
    }

    public void clickKeyBroad() {
        mTextView0.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "0")));
        mTextView1.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "1")));
        mTextView2.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "2")));
        mTextView3.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "3")));
        mTextView4.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "4")));
        mTextView5.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "5")));
        mTextView6.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "6")));
        mTextView7.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "7")));
        mTextView8.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "8")));
        mTextView9.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "9")));
        mTextView000.setOnClickListener(view -> mEditText.setText(mEditText.getText().insert(mEditText.getText().length(), "000")));

        mImageViewDeleted.setOnClickListener(view -> {
            if (mEditText.length() > 0) {
                mEditText.setText(mEditText.getText().delete(mEditText.getText().length() - 1, mEditText.getText().length()));
            }
        });
        mImageViewDeleted.setOnLongClickListener(view -> {
            if (mEditText.length() > 0) {
                mEditText.setText("");
            }
            return false;
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mImageViewClear.setVisibility(View.VISIBLE);
                    mImageViewClear.setOnClickListener(view -> {
                        mEditText.setText("");
                        mImageViewClear.setVisibility(View.GONE);
                    });
                } else {
                    mImageViewClear.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() < 1) {
                    mImageViewClear.setVisibility(View.GONE);
                    mExchangeAdapter = new ExchangeAdapter(ExchangeActivity.this, myListRateSelect, mListRate, 0, oldPosition);
                    mRecyclerViewExChange.setAdapter(mExchangeAdapter);
                    mExchangeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.equals("0") || editable.equals("")) {
                    mImageViewClear.setVisibility(View.GONE);
                    myMoney = Double.valueOf(0);
                    mExchangeAdapter = new ExchangeAdapter(ExchangeActivity.this, myListRateSelect, mListRate, myMoney, oldPosition);
                    mRecyclerViewExChange.setAdapter(mExchangeAdapter);
                    mExchangeAdapter.notifyDataSetChanged();
                } else {
                    try {
                        String money = mEditText.getText().toString();
                        money.replace(".", "");
                        myMoney = Double.parseDouble(money);
                        mExchangeAdapter = new ExchangeAdapter(ExchangeActivity.this, myListRateSelect, mListRate, myMoney, oldPosition);
                        mRecyclerViewExChange.setAdapter(mExchangeAdapter);
                        mExchangeAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.d("SomethingBugs", e.getMessage());
                    }

                }
            }
        });
    }

    public void clickView() {
        mConstraintLayoutChoose.setOnClickListener(view -> {
            mRecyclerViewChooseCountry.setVisibility(View.VISIBLE);
            mConstraintLayoutKeyBroad.setVisibility(View.INVISIBLE);
        });
        mImageViewBack.setOnClickListener(view -> MainActivity.getInstance().backActivity(this));
        mImageViewSetting.setOnClickListener(view ->
                MainActivity.getInstance().runActivity(this, CHOOSE, true));
    }

    public void clickAdapter() {
        mChooseAdapter.setOnCheckListener(position -> {
            mListRate.add(mListRate.get(position));
            mListRate.get(oldPosition).setFavorite(false);
            mListRate.get(position).setFavorite(true);
            oldPosition = position;
            mChooseAdapter.notifyDataSetChanged();
            for (CurrencyIcon currencyIcon : CurrencyIcon.values()) {
                currencyIconList.put(currencyIcon.getCode(), currencyIcon.getRes());
            }
            int icon = currencyIconList.get(mListRate.get(position).getCurrencyCode());
            Picasso.get().load(icon).into(mImageViewChoose);
            mTextViewChoose.setText(mListRate.get(position).getCurrencyCode());
            mRecyclerViewChooseCountry.setVisibility(View.INVISIBLE);
            mConstraintLayoutKeyBroad.setVisibility(View.VISIBLE);
        });
    }

//    public void clickView(){
//        mButton = (Button) findViewById(R.id.btn_click);
//        mEditText = (EditText) findViewById(R.id.et_test);
//        mEditText2 = (EditText) findViewById(R.id.et_test2);
//        mButton.setOnClickListener(view -> getData());
//        try {
//            mEditText.setText(saveActivityList.get(saveActivityList.size() - 1).getString("test1"));
//            mEditText2.setText(saveActivityList.get(saveActivityList.size() - 1).getString("test2"));
//        } catch (Exception e) {
//            Log.d( "AAAAA", e.getMessage());
//        }
//    }

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
//        try {
//        jsonObject = new JSONObject();
//        jsonObject = saveActivity.get(HOME);
//        int id = 0;
//
//            id = Integer.parseInt(jsonObject.getString("activity"));
//
//        MainActivity.getInstance().runActivity(this, id);
//        saveActivityList.remove(saveActivityList.get(saveActivityList.size() - 1));
//        } catch (Exception e) {
//            Log.d( "AAAAA", e.getMessage());
//        }
        MainActivity.getInstance().backActivity(this);
    }
}
