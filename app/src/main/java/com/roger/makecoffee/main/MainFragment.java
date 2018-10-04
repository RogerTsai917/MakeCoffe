package com.roger.makecoffee.main;

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

public class MainFragment extends Fragment implements MainContract.View{
    private MakeCoffeeContract.Presenter mSuperPresenter;
    private MainContract.Presenter mPresenter;
    private BottomNavigationViewEx mBottomNavigation;

    public MainFragment() {
        // Requires empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mBottomNavigation = view.findViewById(R.id.nav_bottom_main);
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
        mPresenter = new MainPresenter(this, getFragmentManager());
        mPresenter.start();
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationViewEx.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_news:
                    mPresenter.transToNewsFragment();
                    return true;

                case R.id.navigation_knowledge:
                    mPresenter.transToCoffeeKnowledgeFragment();
                    return true;

                case R.id.navigation_make_coffee:
                    mPresenter.transToMakeCoffeeFragment();
                    return true;

                default:
            }
            return false;
        }
    };

    @Override
    public void showNewsUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.news));
    }

    @Override
    public void showCoffeeKnowledgeUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.coffee_knowledge));
    }

    @Override
    public void showMakeCoffeeUi() {
        mSuperPresenter.setToolbarTitle(getResources().getString(R.string.make_coffee));
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setSuperPresenter(MakeCoffeeContract.Presenter presenter) {
        mSuperPresenter = presenter;
    }
}
