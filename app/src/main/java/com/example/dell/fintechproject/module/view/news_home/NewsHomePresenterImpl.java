package com.example.dell.fintechproject.module.view.news_home;

import com.example.dell.fintechproject.model.News;
import com.example.dell.fintechproject.module.base_activity.BasePresenter;
import com.example.dell.fintechproject.net.api.ApiFunction;
import com.example.dell.fintechproject.net.api.ApiStatus;
import com.example.dell.fintechproject.net.handle.BaseResponseList;

public class NewsHomePresenterImpl extends BasePresenter<NewsHomeGeneral.NewsHomeView> implements NewsHomeGeneral.NewsHomePresenter {

    public NewsHomePresenterImpl(NewsHomeGeneral.NewsHomeView mView) {
        super(mView);
    }

    @Override
    public void onResponseListener(ApiFunction apiFunction, ApiStatus statusId, Object object) {
        switch (apiFunction) {
            case GET_NEWS: {
                switch (statusId) {
                    case CALL_API_RESULT_SUCCESS: {
                        BaseResponseList<News> newsBaseResponseList = (BaseResponseList<News>) object;
                        getmView().getDataNews(newsBaseResponseList.getListData());
                        break;
                    }
                    case CALL_API_RESULT_FAILURE: {
                        int errorCode = (int) object;
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void getNews() {
        if (!getmView().isNetworkConnect()){
            return;
        }
        mApiMethod.getNews();
    }
}
