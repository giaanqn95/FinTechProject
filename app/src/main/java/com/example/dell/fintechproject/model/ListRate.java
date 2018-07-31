package com.example.dell.fintechproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRate {
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("transfer")
    @Expose
    private String transfer;
    @SerializedName("sell")
    @Expose
    private String sell;

    private boolean isFavorite;

    public ListRate(String currencyCode, String currencyName, String buy, String transfer, String sell) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.buy = buy;
        this.transfer = transfer;
        this.sell = sell;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
