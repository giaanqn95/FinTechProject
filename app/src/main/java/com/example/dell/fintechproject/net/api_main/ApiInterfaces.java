package com.example.dell.fintechproject.net.api_main;

import com.example.dell.fintechproject.model.News;
import com.example.dell.fintechproject.net.handle.BaseResponseList;
import com.example.dell.fintechproject.net.handle.BaseResponseTool;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaces {

    @GET("/public/news")
    Call<BaseResponseList<News>> getNews();
    @GET("/public/exchange")
    Call<BaseResponseTool> getExchange();
}
