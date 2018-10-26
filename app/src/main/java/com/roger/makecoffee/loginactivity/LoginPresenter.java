package com.roger.makecoffee.loginactivity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.user.UserManager;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void transToMakeCoffeeActivity(Activity activity) {
        Intent intent = new Intent(activity, MakeCoffeeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void decodeGoogleSinResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with FireBase
            mView.hideSignInButton();
            GoogleSignInAccount account = task.getResult(ApiException.class);
            UserManager.getInstance().firebaseAuthWithGoogle(mView.getActivity(), account);
        } catch (ApiException e) {
            // Google Sign In failed.
            Log.w(TAG, "Google sign in failed", e);
            mView.showSignInButton();
            mView.showToast("Google sign in failed");
        }
    }

    @Override
    public void start() {

    }
}
