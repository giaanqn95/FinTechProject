package com.example.dell.fintechproject.module.view.choose_country;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
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

import static com.example.dell.fintechproject.module.view.MainActivity.myListRateSelect;

public class ChooseCountryAdapter extends RecyclerView.Adapter<ChooseCountryAdapter.MyViewHolder> {

    Context mContext;
    List<ListRate> mListRates;
    HashMap<String, Integer> currencyIconList = new HashMap<String, Integer>();
    ListRate mRate;
    int icon, i, count = 0;
    OnCheckBoxListener listener;
    SparseBooleanArray itemStateArray;

    public ChooseCountryAdapter(Context mContext, List<ListRate> mListRates) {
        itemStateArray = new SparseBooleanArray();
        this.mContext = mContext;
        this.mListRates = mListRates;
    }

    public void setOnCheckListener(OnCheckBoxListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ChooseCountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_country, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCountryAdapter.MyViewHolder holder, int position) {
        mRate = mListRates.get(position);
        for (CurrencyIcon currencyIcon : CurrencyIcon.values()) {
            currencyIconList.put(currencyIcon.getCode(), currencyIcon.getRes());
        }
        icon = currencyIconList.get(mRate.getCurrencyCode());
        Picasso.get().load(icon).into(holder.mImageView);
        holder.mTextViewName.setText(mRate.getCurrencyName());
        holder.mTextViewCode.setText(mRate.getCurrencyCode());
        if (myListRateSelect.size() > 0 && i < myListRateSelect.size()) {
            Log.d("Log", String.valueOf(i));
            if (myListRateSelect.get(i).getCurrencyCode().equals(mRate.getCurrencyCode())) {
                Picasso.get().load(R.drawable.ic_check_on_3x).into(holder.mImageViewCheck);
                i++;
                mRate.setFavorite(true);
            }
        }
        holder.mConstraintLayout.setOnClickListener(view -> {
            if (listener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (!itemStateArray.get(adapterPosition)) {
                    if (count < 4) {
                        itemStateArray.put(adapterPosition, true);
                        mRate.setFavorite(true);
                        Picasso.get().load(R.drawable.ic_check_on_3x).into(holder.mImageViewCheck);
                        listener.onItemAddClickListener(adapterPosition);
                        count++;
                    }
                } else {
                    if (count > 1) {
                        itemStateArray.put(adapterPosition, false);
                        mRate.setFavorite(false);
                        Picasso.get().load(R.drawable.ic_check_inactive_2x).into(holder.mImageViewCheck);
                        listener.onItemRemoveClickListener(adapterPosition);
                        count--;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListRates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView, mImageViewCheck;
        TextView mTextViewName, mTextViewCode;
        ConstraintLayout mConstraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_iv_choose_country);
            mTextViewName = (TextView) itemView.findViewById(R.id.item_tv_choose_name);
            mTextViewCode = (TextView) itemView.findViewById(R.id.item_tv_choose_code);
            mImageViewCheck = (ImageView) itemView.findViewById(R.id.item_choose_iv_check);
            mConstraintLayout = (ConstraintLayout) itemView.findViewById(R.id.item_cl_choose_country);
        }
    }

    public interface OnCheckBoxListener {
        void onItemAddClickListener(int position);

        void onItemRemoveClickListener(int position);
    }
}
