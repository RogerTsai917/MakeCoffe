package com.roger.makecoffee.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roger.makecoffee.BaseActivity;
import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public class MakeCoffeeActivity extends BaseActivity implements MakeCoffeeContract.View, NavigationView.OnNavigationItemSelectedListener{
    private MakeCoffeeContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private ImageView mToolbarIcon;
    private TextView mToolbarTitle;
    private BottomNavigationViewEx mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);

        setToolbar();
        setBottomNavigation();
//        setDrawerLayout();

        mPresenter = new MakeCoffeePresenter(this, getFragmentManager());
        mPresenter.start();

    }

    private void setToolbar() {
        mToolbar = findViewById(R.id.toolbar_make_coffee_activity);
        mToolbarIcon = findViewById(R.id.imageView_toolbar_icon);
        mToolbarTitle = findViewById(R.id.textView_toolbar_title);
    }

    private void setBottomNavigation() {
        mBottomNavigation = findViewById(R.id.nav_bottom_main_activity);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.enableAnimation(false);
        mBottomNavigation.enableShiftingMode(false);
        mBottomNavigation.enableItemShiftingMode(false);
        mBottomNavigation.setTextVisibility(false);

        mBottomNavigation.setItemHeight((int) getResources().getDimension(R.dimen.nav_bottom_height));

        // this api use px value, so we have to divide density first
        mBottomNavigation.setIconSize(
                getResources().getDimension(R.dimen.nav_bottom_icon_size) / getResources().getDisplayMetrics().density,
                getResources().getDimension(R.dimen.nav_bottom_icon_size) / getResources().getDisplayMetrics().density);

    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_main:
                    transToMain();
                    return true;

                case R.id.navigation_articles:
                    transToArticles();
                    return true;

                case R.id.navigation_search:
                    transToSearch();
                    return true;

                case R.id.navigation_profile:
                    transToProfile();
                    return true;

                default:
            }
            return false;
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }

    @Override
    public void transToMain() {
        mPresenter.transToMain();
    }

    @Override
    public void transToArticles() {
        mPresenter.transToArticles();
    }

    @Override
    public void transToSearch() {
        mPresenter.transToSearch();
    }

    @Override
    public void transToProfile() {
        mPresenter.transToProfile();
    }

    @Override
    public void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching) {
        mPresenter.transToMakeCoffeeDetail(teaching);
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
    public void setPresenter(MakeCoffeeContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
