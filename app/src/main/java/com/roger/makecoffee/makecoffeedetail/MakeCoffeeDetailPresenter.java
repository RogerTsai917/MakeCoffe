package com.roger.makecoffee.makecoffeedetail;

public class MakeCoffeeDetailPresenter implements MakeCoffeeDetailContract.Presenter {
    private MakeCoffeeDetailContract.View mView;

    public MakeCoffeeDetailPresenter(MakeCoffeeDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
