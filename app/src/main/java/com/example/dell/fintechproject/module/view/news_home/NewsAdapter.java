package com.example.dell.fintechproject.module.view.news_home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.fintechproject.model.News;
import com.example.dell.fintechproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<News> mNewsList;
    private Context mContext;

    public NewsAdapter(Context mContext, List<News> mNews) {
        this.mContext = mContext;
        this.mNewsList = mNews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        Picasso.get().load(news.getDisplayImage()).into(holder.mImageView);
        holder.mTextViewTitles.setText(news.getTitle());
        holder.mTextViewDescription.setText(news.getSummary());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextViewTitles;
        TextView mTextViewDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_new_iv);
            mTextViewTitles = (TextView) itemView.findViewById(R.id.item_new_tv_title);
            mTextViewDescription = (TextView) itemView.findViewById(R.id.item_new_tv_description);
        }
    }
}
