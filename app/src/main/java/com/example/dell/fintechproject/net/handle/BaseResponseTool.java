package com.example.dell.fintechproject.net.handle;

import com.example.dell.fintechproject.model.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponseTool {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("object")
    @Expose
    private Objects object;

    @SerializedName("message")
    @Expose
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Objects getObject() {
        return object;
    }

    public void setObject(Objects object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}