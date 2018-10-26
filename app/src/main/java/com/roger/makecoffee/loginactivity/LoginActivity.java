package com.roger.makecoffee.loginactivity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.roger.makecoffee.BaseActivity;
import com.roger.makecoffee.R;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());
        FirebaseApp.initializeApp(this);

        if (UserManager.getInstance().isLoginStatus()) {
            transToMakeCoffeeActivity(this);
        } else {
            init();
        }
    }

    private void init() {
        setContentView(R.layout.activity_login);

        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
    }

    private void transToMakeCoffeeActivity(Activity activity) {
        Intent intent = new Intent(activity, MakeCoffeeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                hideSignInButton();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                UserManager.getInstance().firebaseAuthWithGoogle(this, account);
            } catch (ApiException e) {
                // Google Sign In failed.
                Log.w(TAG, "Google sign in failed", e);
                showSignInButton();
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        setResult(Constants.LOGIN_EXIT);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        setResult(Constants.LOGIN_EXIT);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                UserManager.getInstance().signIn(this);
                break;

            default:
        }
    }

    public void hideSignInButton() {
        mSignInButton.setVisibility(View.GONE);
    }

    public void showSignInButton() {
        mSignInButton.setVisibility(View.VISIBLE);
    }

}
