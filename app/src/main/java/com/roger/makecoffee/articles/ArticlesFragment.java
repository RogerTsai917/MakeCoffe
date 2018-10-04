package com.roger.makecoffee.articles;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.roger.makecoffee.R;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeContract;

public class ArticlesFragment extends Fragment implements ArticlesContract.View {
    private MakeCoffeeContract.Presenter mSuperPresenter;
    private ArticlesContract.Presenter mPresenter;
    private BottomNavigationViewEx mBottomNavigation;

    public ArticlesFragment() {
        // Requires empty public constructor
    }

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        mBottomNavigation = view.findViewById(R.id.nav_bottom_articles);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.enableAnimation(false);
        mBottomNavigation.enableShiftingMode(false);
        mBottomNavigation.enableItemShiftingMode(false);
        mBottomNavigation.setTextVisibility(false);

        mBottomNavigation.setItemHeight((int) getResources().getDimension(R.dimen.sub_nav_bottom_height));

        // this api use px value, so we have to divide density first
        mBottomNavigation.setIconSize(
                getResources().getDimension(R.dimen.sub_nav_bottom_icon_size) / getResources().getDisplayMetrics().density,
                getResources().getDimension(R.dimen.sub_nav_bottom_icon_size) / getResources().getDisplayMetrics().density);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ArticlesPresenter(this, getFragmentManager());
        mPresenter.start();
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_articles_list:
                    mPresenter.transToArticlesListFragment();
                    return true;

                case R.id.navigation_following:
                    mPresenter.transToFollowingFragment();
                    return true;

                case R.id.navigation_liked:
                    mPresenter.transToLikedFragment();
                    return true;

                default:
            }
            return false;
        }
    };

    @Override
    public void showArticlesListUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.articles));
    }

    @Override
    public void showFollowingUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.following));
    }

    @Override
    public void showLikedUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.liked));
    }

    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setSuperPresenter(MakeCoffeeContract.Presenter presenter) {
        mSuperPresenter = presenter;
    }
}
