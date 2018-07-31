package com.example.dell.fintechproject;

public enum CurrencyIcon {
    UCO("UCO", 0),
    VNA("VNA", R.drawable.ic_vn_3x),
    AUD("AUD", R.drawable.ic_au_3x),
    CHF("CHF", R.drawable.chf),
    DKK("DKK", R.drawable.dk),
    INR("INR", R.drawable.ir),
    KWD("KWD", R.drawable.kd),
    NOK("NOK", R.drawable.nk),
    RUB("RUB", R.drawable.rr),
    SAR("SAR", R.drawable.sr),
    SEK("SEK", R.drawable.sk),
    USD("USD", R.drawable.ic_us_3x),
    JPY("JPY", R.drawable.ic_jp_3x),
    HKD("HKD", R.drawable.ic_hk_3x),
    CAD("CAD", R.drawable.ic_ca_3x),
    BRZ("BRZ", R.drawable.ic_br_3x),
    MYR("MYR", R.drawable.ic_my_3x),
    GBP("GBP", R.drawable.ic_gb_3x),
    SGD("SGD", R.drawable.ic_sg_3x),
    THB("THB", R.drawable.ic_th_3x),
    KRW("KRW", R.drawable.ic_kr_3x),
    CHN("CHN", R.drawable.ic_cn_3x),
    EUR("EUR", R.drawable.ic_eu_3x);

    private String code;
    private int res;

    CurrencyIcon(String code, int res) {
        this.code = code;
        this.res = res;
    }

    public int getRes() {
        return res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public static CurrencyIcon find(String filter) {
        for (CurrencyIcon ci : values()) {
            if (ci.code.equals(filter)) {
                return ci;
            }
        }
        return UCO;
    }
}
