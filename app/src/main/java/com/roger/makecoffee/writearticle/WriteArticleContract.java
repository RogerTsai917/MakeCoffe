package com.roger.makecoffee.writearticle;

import android.app.Activity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.Article;
import com.roger.makecoffee.objects.define.NewArticle;

public interface WriteArticleContract {

    interface View extends BaseView<Presenter> {
        void showToast(String content);

        Activity returnActivity();

        void showUploadingDialog();

        void hideUploadingDialog();

        void showChangeCoffeeFlavorDialog();

        void hideChangeCoffeeFlavorDialog();

        void backPress();
    }

    interface Presenter extends BasePresenter {

        void postArticle(NewArticle article);
    }
}
