package com.roger.makecoffee.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.roger.makecoffee.MakeCoffee;
import com.roger.makecoffee.R;
import com.roger.makecoffee.utils.Constants;

import java.util.concurrent.Executor;

public class UserManager {
    private static final String TAG = "UserManager";
    private static UserManager mUserManager;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private UserManager() {
        // [START config_sign_in]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(MakeCoffee.getAppContext().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_sign_in]

        mGoogleSignInClient = GoogleSignIn.getClient(MakeCoffee.getAppContext(), gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    public static synchronized UserManager getInstance() {
        if (mUserManager == null) {
            mUserManager = new UserManager();
        }
        return mUserManager;
    }

    public void signIn(Activity activity) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut();

        MakeCoffee.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit();
    }

    public void firebaseAuthWithGoogle(Activity activity, GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveUserInfo(user);
                        } else {
                            // If sign in fails, display a message.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }

                    }
                });
    }

    private void saveUserInfo(FirebaseUser user) {
        Log.d(TAG, "UserInfo Name: " + user.getDisplayName());
        Log.d(TAG, "UserInfo Email: " + user.getEmail());
        Log.d(TAG, "UserInfo Photo Url: " + user.getPhotoUrl().toString());
        Log.d(TAG, "UserInfo Uid: " + user.getUid());

        MakeCoffee.getAppContext().getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE).edit()
                .putString(Constants.USER_NAME, user.getDisplayName())
                .putString(Constants.USER_EMAIL, user.getEmail())
                .putString(Constants.USER_PHOTO_URL, user.getPhotoUrl().toString())
                .putString(Constants.USER_UID, user.getUid())
                .commit();
    }

    public String getUserName() {
        return MakeCoffee.getAppContext()
                .getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
                .getString(Constants.USER_NAME, null);
    }

    public String getUserEmail() {
        return MakeCoffee.getAppContext()
                .getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
                .getString(Constants.USER_EMAIL, null);
    }

    public String getUserPhotoUrl() {
        return MakeCoffee.getAppContext()
                .getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
                .getString(Constants.USER_PHOTO_URL, null);
    }

    public String getUserUid() {
        return MakeCoffee.getAppContext()
                .getSharedPreferences(Constants.USER_DATA, Context.MODE_PRIVATE)
                .getString(Constants.USER_UID, null);
    }

    public boolean isLoginStatus() {

        return getUserUid() != null;
    }
}
