package com.roger.makecoffee.articles;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface ArticlesContract {

    interface View extends BaseView<Presenter> {
        void showArticlesListUi();

        void showFollowingUi();

        void showLikedUi();
    }

    interface Presenter extends BasePresenter {
        void transToArticlesListFragment();

        void transToFollowingFragment();

        void transToLikedFragment();
    }
}
