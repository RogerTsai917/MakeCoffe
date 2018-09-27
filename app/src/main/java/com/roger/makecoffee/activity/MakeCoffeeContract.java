package com.roger.makecoffee.activity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface MakeCoffeeContract {

    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String title);
    }

    interface Presenter extends BasePresenter {

        void transToMain();

        void transToArticles();

        void transToSearch();

        void transToProfile();

        void setToolbarTitle(String title);
    }

}
