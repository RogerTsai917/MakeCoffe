package com.roger.makecoffee.liked;

public class LikedPresenter implements LikedContract.Presenter {
    private LikedContract.View mView;

    LikedPresenter(LikedContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
