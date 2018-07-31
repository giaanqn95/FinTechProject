package com.example.dell.fintechproject.module.view.currency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.fintechproject.CurrencyIcon;
import com.example.dell.fintechproject.R;
import com.example.dell.fintechproject.model.ListRate;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    Context mContext;
    List<ListRate> mListRates;
    HashMap<String, Integer> currencyIconList = new HashMap<String, Integer>();
    int icon;

    public CurrencyAdapter(Context mContext, List<ListRate> mListRates) {
        this.mContext = mContext;
        this.mListRates = mListRates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListRate listRate = mListRates.get(position);
        for (CurrencyIcon currencyIcon: CurrencyIcon.values()){
            currencyIconList.put(currencyIcon.getCode(), currencyIcon.getRes());
        }
        holder.mTextViewCountry.setText(listRate.getCurrencyCode());
        holder.mTextViewSell.setText(listRate.getSell());
        holder.mTextViewBuy.setText(listRate.getBuy());
        CurrencyIcon.find(listRate.getCurrencyCode());
        icon = currencyIconList.get(listRate.getCurrencyCode());
        Picasso.get().load(icon).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mListRates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextViewCountry, mTextViewBuy, mTextViewSell;

        public MyViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.item_currency_iv);
            mTextViewCountry = (TextView) view.findViewById(R.id.item_currency_tv_country);
            mTextViewBuy = (TextView) view.findViewById(R.id.item_currency_tv_mua);
            mTextViewSell = (TextView) view.findViewById(R.id.item_currency_tv_ban);
        }
    }
}
