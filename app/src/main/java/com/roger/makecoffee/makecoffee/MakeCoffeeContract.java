package com.roger.makecoffee.makecoffee;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface MakeCoffeeContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
