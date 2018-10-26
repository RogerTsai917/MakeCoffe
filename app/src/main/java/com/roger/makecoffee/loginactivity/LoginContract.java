package com.roger.makecoffee.loginactivity;

import android.app.Activity;
import android.content.Intent;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void hideSignInButton();

        void showSignInButton();

        Activity getActivity();

        void showToast(String string);
    }

    interface Presenter extends BasePresenter {
        void transToMakeCoffeeActivity(Activity activity);

        void decodeGoogleSinResult(Intent data);

    }
}
