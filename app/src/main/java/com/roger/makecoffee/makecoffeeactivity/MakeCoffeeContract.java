package com.roger.makecoffee.makecoffeeactivity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public interface MakeCoffeeContract {

    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String title);

        void transToMain();

        void transToArticles();

        void transToSearch();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void transToKnowledgeDetail(CoffeeKnowledgeCollection collection);

        void showToolbarAndNavBottom();

        void hideToolbarAndNavBottom();
    }

    interface Presenter extends BasePresenter {

        void transToMain();

        void transToArticles();

        void transToSearch();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void transToKnowledgeDetail(CoffeeKnowledgeCollection collection);

        void setToolbarTitle(String title);
    }

}
