package com.roger.makecoffee.activity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public interface MakeCoffeeContract {

    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String title);

        void transToMain();

        void transToArticles();

        void transToSearch();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void showToolbarAndNavBottom();

        void hideToolbarAndNavBottom();
    }

    interface Presenter extends BasePresenter {

        void transToMain();

        void transToArticles();

        void transToSearch();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void setToolbarTitle(String title);
    }

}
