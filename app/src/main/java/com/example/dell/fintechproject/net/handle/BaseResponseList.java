package com.example.dell.fintechproject.net.handle;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by E7440 on 6/11/2018.
 */

public class BaseResponseList<T> {
    @SerializedName("code")
    private int errorCode;
    @SerializedName("data")
    private List<T> listData;
    @SerializedName("messages")
    private String message;

    public BaseResponseList() {
    }


    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public List<T> getListData() {
        return listData;
    }
}
