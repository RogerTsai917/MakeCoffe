package com.roger.makecoffee.writearticle;

import android.app.Activity;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.Article;

public interface WriteArticleContract {

    interface View extends BaseView<Presenter> {
        void showToast(String content);

        Activity returnActivity();

        void showUploadingDialog();

        void hideUploadingDialog();

        void backPress();
    }

    interface Presenter extends BasePresenter {

        void postArticle(Article article);
    }
}
