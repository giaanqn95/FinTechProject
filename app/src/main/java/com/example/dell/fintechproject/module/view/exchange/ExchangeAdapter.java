package com.example.dell.fintechproject.module.view.exchange;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.fintechproject.CurrencyIcon;
import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.ListRate;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.MyViewHolder> {
    private static final String FORMAT_WITH_DECIMAL_COMMA = "###,##0.00";
    DecimalFormat formatter;
    Context mContext;
    List<ListRate> mListRates;
    HashMap<String, Integer> currencyIconList = new HashMap<String, Integer>();
    int icon;
    List<ListRate> myListRate;
    double money, moneyEnter;
    int myPosition;

    public ExchangeAdapter(Context mContext, List<ListRate> mListRates, List<ListRate> myListRate, double moneyEnter, int myPosition) {
        this.mContext = mContext;
        this.mListRates = mListRates;
        this.myListRate = myListRate;
        this.moneyEnter = moneyEnter;
        this.myPosition = myPosition;
        formatter = new DecimalFormat(FORMAT_WITH_DECIMAL_COMMA, new DecimalFormatSymbols(Locale.US));
    }


    private String formatString(double number) {
        return formatter.format(number);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListRate listRate = mListRates.get(position);
        for (CurrencyIcon currencyIcon : CurrencyIcon.values()) {
            currencyIconList.put(currencyIcon.getCode(), currencyIcon.getRes());
        }
        CurrencyIcon.find(listRate.getCurrencyCode());
        icon = currencyIconList.get(listRate.getCurrencyCode());
        Picasso.get().load(icon).into(holder.mImageView);
        holder.mTextViewCountry.setText(listRate.getCurrencyCode());
        if (moneyEnter == 0) {
            holder.mTextViewMoney.setText("0");
        }
        if (listRate.getBuy().equals("0")) {
            holder.mTextViewMoney.setText("0");
        }
        if (moneyEnter != 0) {
            try {
                if (myListRate.get(myPosition).equals("VNA")) {
                    money = moneyEnter / Double.parseDouble(listRate.getBuy());
                    if (money <= 999999999.0) {
                        holder.mTextViewMoney.setText(String.format(Locale.US, "%.9f", money));
                    } else {
                        holder.mTextViewMoney.setText(String.format(Locale.US, "%.12f", money));
                    }
                } else {
                    if (!listRate.getBuy().equals("0")) {
                        double abc = moneyEnter * Double.parseDouble(myListRate.get(myPosition).getBuy()) / Double.parseDouble(listRate.getBuy());
                        if (abc <= 999999999.0) {
                            holder.mTextViewMoney.setText(formatString(abc));
                        } else {
                            holder.mTextViewMoney.setText(String.format(Locale.US, "%6.3e", Double.parseDouble(myListRate.get(myPosition).getBuy()) / Double.parseDouble(listRate.getBuy())));
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("ErrorSomething", e.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mListRates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextViewCountry, mTextViewMoney;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_exchange_iv);
            mTextViewCountry = (TextView) itemView.findViewById(R.id.item_exchange_tv_country);
            mTextViewMoney = (TextView) itemView.findViewById(R.id.item_exchange_tv);
        }
    }
}
