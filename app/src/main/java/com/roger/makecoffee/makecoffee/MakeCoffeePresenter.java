package com.roger.makecoffee.makecoffee;

public class MakeCoffeePresenter implements MakeCoffeeContract.Presenter {
    private MakeCoffeeContract.View mMakeCoffeeView;

    public MakeCoffeePresenter(MakeCoffeeContract.View view) {
        mMakeCoffeeView = view;
        mMakeCoffeeView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
