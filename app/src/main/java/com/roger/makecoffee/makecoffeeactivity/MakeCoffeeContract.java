package com.roger.makecoffee.makecoffeeactivity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public interface MakeCoffeeContract {

    interface View extends BaseView<Presenter> {
        void setToolbarTitle(String title);

        void transToMakeCoffee();

        void transToKnowledge();

        void transToNews();

        void transToArticles();

        void transToLiked();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void transToKnowledgeDetail(CoffeeKnowledgeCollection collection);

        void transToWriteArticle();

        void showToolbarAndNavBottom();

        void hideToolbarAndNavBottom();
    }

    interface Presenter extends BasePresenter {

        void transToMakeCoffee();

        void transToKnowledge();

        void transToNews();

        void transToArticles();

        void transToLiked();

        void transToProfile();

        void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching);

        void transToKnowledgeDetail(CoffeeKnowledgeCollection collection);

        void transToWriteArticle();

        void setToolbarTitle(String title);
    }

}
