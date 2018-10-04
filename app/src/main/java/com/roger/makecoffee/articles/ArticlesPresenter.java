package com.roger.makecoffee.articles;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.roger.makecoffee.R;
import com.roger.makecoffee.articleslist.ArticlesListFragment;
import com.roger.makecoffee.following.FollowingFragment;
import com.roger.makecoffee.liked.LikedFragment;

public class ArticlesPresenter implements ArticlesContract.Presenter {
    private ArticlesContract.View mArticlesView;
    private FragmentManager mChildFragmentManager;

    private ArticlesListFragment mArticlesListFragment;
    private FollowingFragment mFollowingFragment;
    private LikedFragment mLikedFragment;

    public ArticlesPresenter(ArticlesContract.View view, FragmentManager fragmentManager) {
        mArticlesView = view;
        mArticlesView.setPresenter(this);
        mChildFragmentManager = fragmentManager;
    }

    @Override
    public void transToArticlesListFragment() {
        mArticlesView.showArticlesListUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mArticlesListFragment == null) mArticlesListFragment = ArticlesListFragment.newInstance();
        if (mFollowingFragment != null) transaction.hide(mFollowingFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (!mArticlesListFragment.isAdded()) {
            transaction.add(R.id.frameLayout_articles_container, mArticlesListFragment);
        } else {
            transaction.show(mArticlesListFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToFollowingFragment() {
        mArticlesView.showFollowingUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mFollowingFragment == null) mFollowingFragment = FollowingFragment.newInstance();
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (!mFollowingFragment.isAdded()) {
            transaction.add(R.id.frameLayout_articles_container, mFollowingFragment);
        } else {
            transaction.show(mFollowingFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToLikedFragment() {
        mArticlesView.showLikedUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mLikedFragment == null) mLikedFragment = LikedFragment.newInstance();
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mFollowingFragment != null) transaction.hide(mFollowingFragment);
        if (!mLikedFragment.isAdded()) {
            transaction.add(R.id.frameLayout_articles_container, mLikedFragment);
        } else {
            transaction.show(mLikedFragment);
        }
        transaction.commit();
    }

    @Override
    public void start() {
        transToArticlesListFragment();
    }
}
