package com.example.dell.fintechproject;


import com.example.dell.fintechproject.module.view.currency.CurrencyActivity;
import com.example.dell.fintechproject.module.view.maps.MapsActivity;
import com.example.dell.fintechproject.module.view.news_home.HomeNews;
import com.example.dell.fintechproject.module.view.exchange.ExchangeActivity;

public enum NameClass {

    HOME(1, HomeNews.class),
    EXCHANGE(2, ExchangeActivity.class),
    CURRENCY(3, CurrencyActivity.class),
    MAP(4, MapsActivity.class);


    private int key;
    private Class name;

    NameClass(int key, Class name) {
        this.key = key;
        this.name = name;
    }

    public static NameClass filter(int key) {
        for (NameClass item : values()) {
            if (item.key == key) {
                return item;
            }
        }
        return HOME;
    }

    public int getKey() {
        return key;
    }

    public Class getName() {
        return name;
    }
}
