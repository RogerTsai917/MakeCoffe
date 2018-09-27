package com.roger.makecoffee.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roger.makecoffee.BaseActivity;
import com.roger.makecoffee.R;

public class MakeCoffeeActivity extends BaseActivity implements MakeCoffeeContract.View, NavigationView.OnNavigationItemSelectedListener{
    private MakeCoffeeContract.Presenter mPresenter;
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
        mToolbarTitle = findViewById(R.id.textView_toolbar_title);
        setToolbarTitle(getResources().getString(R.string.news));
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
                    mPresenter.transToMain();
                    return true;

                case R.id.navigation_articles:
                    mPresenter.transToArticles();
                    return true;

                case R.id.navigation_search:
                    mPresenter.transToSearch();
                    return true;

                case R.id.navigation_profile:
                    mPresenter.transToProfile();
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
    public void setPresenter(MakeCoffeeContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
