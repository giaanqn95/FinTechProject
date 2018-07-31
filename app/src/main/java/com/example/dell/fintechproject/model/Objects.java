package com.example.dell.fintechproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Objects {
    @SerializedName("listRate")
    @Expose
    private List<ListRate> listRate;
    @SerializedName("timeQuery")
    @Expose
    private long timeQuery;

    public List<ListRate> getListRate() {
        return listRate;
    }

    public void setListRate(List<ListRate> listRate) {
        this.listRate = listRate;
    }

    public long getTimeQuery() {
        return timeQuery;
    }

    public void setTimeQuery(long timeQuery) {
        this.timeQuery = timeQuery;
    }
}
