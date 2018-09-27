package com.roger.makecoffee.main;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showNewsUi();

        void showCoffeeKnowledgeUi();

        void showMakeCoffeeUi();

    }

    interface Presenter extends BasePresenter {

        void transToNewsFragment();

        void transToCoffeeKnowledgeFragment();

        void transToMakeCoffeeFragment();

    }
}
