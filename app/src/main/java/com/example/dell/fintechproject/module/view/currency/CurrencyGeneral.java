package com.example.dell.fintechproject.module.view.currency;

import com.example.dell.fintechproject.model.ListRate;
import com.example.dell.fintechproject.module.base_activity.BaseView;

import java.util.List;

public interface CurrencyGeneral {

    interface CurrencyPresenter{
        void getCurrency();
    }
    interface CurrencyView extends BaseView {
        void getDataCurrency(List<ListRate> listRates);
    }
}
