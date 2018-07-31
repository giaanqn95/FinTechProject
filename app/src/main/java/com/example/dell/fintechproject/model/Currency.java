package com.example.dell.fintechproject.model;

import android.graphics.Bitmap;

/**
 * Created by chaum on 7/31/2017.
 */

public class Currency {
    private Bitmap imgFlag;
    private String currencyName;
    private String currencyShortName;
    private double buyInVal, sellOutVal;
    private boolean isFavorite;

    public Currency(Bitmap imgFlag, String currencyName, String currencyShortName, double buyInVal, double sellOutVal, boolean isFavorite) {
        this.imgFlag = imgFlag;
        this.currencyName = currencyName;
        this.currencyShortName = currencyShortName;
        this.buyInVal = buyInVal;
        this.sellOutVal = sellOutVal;
        this.isFavorite = isFavorite;
    }

    public Bitmap getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(Bitmap imgFlag) {
        this.imgFlag = imgFlag;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getBuyInVal() {
        return buyInVal;
    }

    public void setBuyInVal(double buyInVal) {
        this.buyInVal = buyInVal;
    }

    public double getSellOutVal() {
        return sellOutVal;
    }

    public void setSellOutVal(double sellOutVal) {
        this.sellOutVal = sellOutVal;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public void setCurrencyShortName(String currencyShortName) {
        this.currencyShortName = currencyShortName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return getCurrencyShortName() + " " + getCurrencyName() + " " + isFavorite();
    }
}
