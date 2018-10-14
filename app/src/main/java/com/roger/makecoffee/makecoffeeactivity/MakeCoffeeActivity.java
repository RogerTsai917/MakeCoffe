package com.roger.makecoffee.makecoffeeactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roger.makecoffee.BaseActivity;
import com.roger.makecoffee.R;
import com.roger.makecoffee.loginactivity.LoginActivity;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

public class MakeCoffeeActivity extends BaseActivity implements MakeCoffeeContract.View {
    private MakeCoffeeContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private ImageView mToolbarIcon;
    private TextView mToolbarTitle;
    private BottomNavigationViewEx mBottomNavigation;
    private DrawerLayout mDrawerLayout;
    private ConstraintLayout mLogoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        if (UserManager.getInstance().isLoginStatus()) {
            init();
        } else {

            popLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Constants.LOGIN_SUCCESS) {
            init();
            UserManager.getInstance().checkFireStoreUserInfo();
        }
    }

    private void popLogin() {
        startActivityForResult(new Intent(mContext, LoginActivity.class), Constants.LOGIN_ACTIVITY);
    }

    private void init() {
        setContentView(R.layout.activity_main_drawer);

        setToolbar();
        setBottomNavigation();
        setDrawerLayout();

        mPresenter = new MakeCoffeePresenter(this, getFragmentManager());
        setToolbarTitle(getResources().getString(R.string.make_coffee));
        mPresenter.start();

    }

    private void setToolbar() {
        mToolbar = findViewById(R.id.toolbar_make_coffee_activity);
        mToolbarIcon = findViewById(R.id.imageView_toolbar_icon);
        mToolbarTitle = findViewById(R.id.textView_toolbar_title);

        mToolbarIcon.setOnClickListener(mOnClickListener);
    }

    private void setBottomNavigation() {
        mBottomNavigation = findViewById(R.id.nav_bottom_main_activity);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.enableAnimation(false);
        mBottomNavigation.enableShiftingMode(false);
        mBottomNavigation.enableItemShiftingMode(false);
        mBottomNavigation.setTextVisibility(true);

        mBottomNavigation.setItemHeight((int) getResources().getDimension(R.dimen.nav_bottom_height));

        // this api use px value, so we have to divide density first
        mBottomNavigation.setIconSize(
                getResources().getDimension(R.dimen.nav_bottom_icon_size) / getResources().getDisplayMetrics().density,
                getResources().getDimension(R.dimen.nav_bottom_icon_size) / getResources().getDisplayMetrics().density);
    }

    private void setDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawerLayout_main_activity);

        mDrawerLayout.setFitsSystemWindows(true);
        mDrawerLayout.setClipToPadding(false);

        mLogoutView = findViewById(R.id.constraintLayout_nav_logout);
        mLogoutView.setOnClickListener(mOnClickListener);
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {

                case R.id.navigation_make_coffee:
                    transToMakeCoffee();
                    return true;

                case R.id.navigation_knowledge:
                    transToKnowledge();
                    return true;

                case R.id.navigation_news:
                    transToNews();
                    return true;

                case R.id.navigation_articles:
                    transToArticles();
                    return true;

                case R.id.navigation_liked:
                    transToLiked();
                    return true;

                default:
            }
            return false;
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_toolbar_icon:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    break;

                case R.id.constraintLayout_nav_logout:
                    mPresenter.logout();
                    break;

                default:
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

    @Override
    public void transToMakeCoffee() {
        setToolbarTitle(getResources().getString(R.string.make_coffee));
        mPresenter.transToMakeCoffee();
    }

    @Override
    public void transToKnowledge() {
        setToolbarTitle(getResources().getString(R.string.coffee_knowledge));
        mPresenter.transToKnowledge();
    }

    @Override
    public void transToNews() {
        setToolbarTitle(getResources().getString(R.string.news));
        mPresenter.transToNews();
    }

    @Override
    public void transToArticles() {
        setToolbarTitle(getResources().getString(R.string.articles));
        mPresenter.transToArticles();
    }

    @Override
    public void transToLiked() {
        setToolbarTitle(getResources().getString(R.string.liked));
        mPresenter.transToLiked();
    }

    @Override
    public void transToProfile() {
        setToolbarTitle(getResources().getString(R.string.profile));
        mPresenter.transToProfile();
    }

    @Override
    public void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching) {
        mPresenter.transToMakeCoffeeDetail(teaching);
    }

    @Override
    public void transToKnowledgeDetail(CoffeeKnowledgeCollection collection) {
        mPresenter.transToKnowledgeDetail(collection);
    }

    @Override
    public void transToWriteArticle() {
        mPresenter.transToWriteArticle();
    }

    @Override
    public void showToolbarAndNavBottom() {
        mToolbar.setVisibility(View.VISIBLE);
        mToolbarIcon.setVisibility(View.VISIBLE);
        mToolbarTitle.setVisibility(View.VISIBLE);
        mBottomNavigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbarAndNavBottom() {
        mToolbar.setVisibility(View.GONE);
        mToolbarIcon.setVisibility(View.GONE);
        mToolbarTitle.setVisibility(View.GONE);
        mBottomNavigation.setVisibility(View.GONE);
    }

    @Override
    public Activity getMakeCoffeeActivity() {
        return this;
    }

    @Override
    public void setPresenter(MakeCoffeeContract.Presenter presenter) {
        mPresenter = presenter;
    }



}
