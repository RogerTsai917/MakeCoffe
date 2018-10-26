package com.roger.makecoffee.loginactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.FirebaseApp;
import com.roger.makecoffee.BaseActivity;
import com.roger.makecoffee.R;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends BaseActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter mPresenter;
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialFireBase();

        mPresenter = new LoginPresenter(this);

        if (UserManager.getInstance().isLoginStatus()) {
            mPresenter.transToMakeCoffeeActivity(this);
        } else {
            init();
        }
    }

    private void  initialFireBase() {
        Fabric.with(this, new Crashlytics());
        FirebaseApp.initializeApp(this);
    }

    private void init() {
        setContentView(R.layout.activity_login);

        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            mPresenter.decodeGoogleSinResult(data);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                UserManager.getInstance().signIn(this);
                break;

            default:
        }
    }

    @Override
    public void hideSignInButton() {
        mSignInButton.setVisibility(View.GONE);
    }

    @Override
    public void showSignInButton() {
        mSignInButton.setVisibility(View.VISIBLE);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
