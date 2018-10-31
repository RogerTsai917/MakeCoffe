package com.roger.makecoffee.news;

import android.app.Activity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface NewsContract {

    interface View extends BaseView<Presenter> {
        void showNews();

        Activity getMakeCoffeeActivity();
    }

    interface Presenter extends BasePresenter {

        void loadNews();

        void openWebView(String url);
    }
}
