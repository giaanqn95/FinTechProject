package com.example.dell.fintechproject.module.base_activity;

import com.example.dell.fintechproject.net.api.ApiListener;
import com.example.dell.fintechproject.net.api.ApiMethod;

/**
 * Created by E7440 on 6/11/2018.
 */

public abstract class BasePresenter<T extends BaseView> implements ApiListener {
    protected ApiMethod mApiMethod;
    private T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
        mApiMethod = new ApiMethod();
        mApiMethod.setmApiListener(this);
    }

    public T getmView() {
        return mView;
    }

    protected int loginAgain(int errorCode) {
        if (errorCode == 400 || errorCode == 401 || errorCode == 403 || errorCode == 405 ||
                errorCode == 406 || errorCode == 440) {
            return 401;
        }
        return -1;
    }
}
