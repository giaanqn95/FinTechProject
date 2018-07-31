package com.example.dell.fintechproject.net.handle;


import com.google.gson.annotations.SerializedName;

/**
 * Created by E7440 on 6/11/2018.
 */

public class BaseResponseObject<T> {

    @SerializedName("message")
    protected String message;
    @SerializedName("code")
    protected int errorCode;
    @SerializedName("data")
    private T data;


    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }

}
