package com.roger.makecoffee.news;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface NewsContract {

    interface View extends BaseView<Presenter> {
        void showNews();

        void showNewsDetail();
    }

    interface Presenter extends BasePresenter {

        void loadNews();
    }
}
