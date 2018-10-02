package com.roger.makecoffee.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import com.roger.makecoffee.R;
import com.roger.makecoffee.articles.ArticlesFragment;
import com.roger.makecoffee.main.MainFragment;
import com.roger.makecoffee.main.MainPresenter;
import com.roger.makecoffee.makecoffeedetail.MakeCoffeeDetailFragment;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;
import com.roger.makecoffee.profile.ProfileFragment;
import com.roger.makecoffee.search.SearchFragment;

public class MakeCoffeePresenter implements MakeCoffeeContract.Presenter {
    private static final String MAIN = "MAIN";
    private static final String ARTICLES = "ARTICLES";
    private static final String SEARCH = "SEARCH";
    private static final String PROFILE = "PROFILE";
    private static final String MAKE_COFFEE_DETAIL = "MAKE_COFFEE_DETAIL";

    private MakeCoffeeContract.View mMakeCoffeeView;
    private FragmentManager mFragmentManager;

    private MainFragment mMainFragment;
    private ArticlesFragment mArticlesFragment;
    private SearchFragment mSearchFragment;
    private ProfileFragment mProfileFragment;

    public MakeCoffeePresenter(MakeCoffeeContract.View makeCoffeeView, FragmentManager fragmentManager) {
        mMakeCoffeeView = makeCoffeeView;
        makeCoffeeView.setPresenter(this);
        mFragmentManager = fragmentManager;
    }

    @Override
    public void transToMain() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mMainFragment == null) mMainFragment = MainFragment.newInstance();
        if (mArticlesFragment != null) transaction.hide(mArticlesFragment);
        if (mSearchFragment != null) transaction.hide(mSearchFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mMainFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mMainFragment, MAIN);
        } else {
            transaction.show(mMainFragment);
        }
        mMainFragment.setSuperPresenter(this);
        transaction.commit();
    }

    @Override
    public void transToArticles() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mArticlesFragment == null) mArticlesFragment = ArticlesFragment.newInstance();
        if (mMainFragment != null) transaction.hide(mMainFragment);
        if (mSearchFragment != null) transaction.hide(mSearchFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mArticlesFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mArticlesFragment, ARTICLES);
        } else {
            transaction.show(mArticlesFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToSearch() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mSearchFragment == null) mSearchFragment = SearchFragment.newInstance();
        if (mMainFragment != null) transaction.hide(mMainFragment);
        if (mArticlesFragment != null) transaction.hide(mArticlesFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mSearchFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mSearchFragment, SEARCH);
        } else {
            transaction.show(mSearchFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToProfile() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mProfileFragment == null) mProfileFragment = ProfileFragment.newInstance();
        if (mMainFragment != null) transaction.hide(mMainFragment);
        if (mArticlesFragment != null) transaction.hide(mArticlesFragment);
        if (mSearchFragment != null) transaction.hide(mSearchFragment);
        if (!mProfileFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mProfileFragment, PROFILE);
        } else {
            transaction.show(mProfileFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToMakeCoffeeDetail(MakeCoffeeTeaching teaching) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mMainFragment != null && !mMainFragment.isHidden()) {
            transaction.hide(mMainFragment);
            transaction.addToBackStack(MAIN);
        }
        if (mArticlesFragment != null && !mArticlesFragment.isHidden()) {
            transaction.hide(mArticlesFragment);
            transaction.addToBackStack(ARTICLES);
        }
        if (mArticlesFragment != null && !mArticlesFragment.isHidden()) {
            transaction.hide(mArticlesFragment);
            transaction.addToBackStack(ARTICLES);
        }
        if (mSearchFragment != null && !mSearchFragment.isHidden()) {
            transaction.hide(mSearchFragment);
            transaction.addToBackStack(SEARCH);
        }
        MakeCoffeeDetailFragment makeCoffeeDetailFragment = MakeCoffeeDetailFragment.newInstance(teaching);
        transaction.add(R.id.frameLayout_make_coffee_container, makeCoffeeDetailFragment, MAKE_COFFEE_DETAIL);
        transaction.commit();
    }

    @Override
    public void start() {
        transToMain();
    }

    @Override
    public void setToolbarTitle(String title) {
        mMakeCoffeeView.setToolbarTitle(title);
    }

}
