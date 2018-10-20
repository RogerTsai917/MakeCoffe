package com.roger.makecoffee.articleslist;

public class ArticlesListPresenter implements ArticlesListContract.Presenter {
    private ArticlesListContract.View mView;


    public ArticlesListPresenter(ArticlesListContract.View view) {
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }
}
