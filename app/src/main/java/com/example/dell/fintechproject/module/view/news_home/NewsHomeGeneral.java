package com.example.dell.fintechproject.module.view.news_home;

import com.example.dell.fintechproject.model.News;
import com.example.dell.fintechproject.module.base_activity.BaseView;

import java.util.List;

public interface NewsHomeGeneral {

    interface NewsHomePresenter{
        void getNews();
    }

    interface NewsHomeView extends BaseView {
        void getDataNews(List<News> news);
    }
}
