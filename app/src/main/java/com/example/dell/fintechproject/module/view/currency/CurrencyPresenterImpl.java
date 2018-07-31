package com.example.dell.fintechproject.module.view.currency;

import com.example.dell.fintechproject.module.base_activity.BasePresenter;
import com.example.dell.fintechproject.net.api.ApiFunction;
import com.example.dell.fintechproject.net.api.ApiStatus;
import com.example.dell.fintechproject.net.handle.BaseResponseTool;

public class CurrencyPresenterImpl extends BasePresenter<CurrencyGeneral.CurrencyView> implements CurrencyGeneral.CurrencyPresenter {

    public CurrencyPresenterImpl(CurrencyGeneral.CurrencyView mView) {
        super(mView);
    }

    @Override
    public void onResponseListener(ApiFunction apiFunction, ApiStatus statusId, Object object) {
        switch (apiFunction){
            case GET_EXCHANGE: {
                switch (statusId){
                    case CALL_API_RESULT_SUCCESS:{
                        BaseResponseTool baseResponseTool = (BaseResponseTool) object;
                        getmView().getDataCurrency(baseResponseTool.getObject().getListRate());
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void getCurrency() {
        if (!getmView().isNetworkConnect()){
            return;
        }
        mApiMethod.getExchange();
    }
}
