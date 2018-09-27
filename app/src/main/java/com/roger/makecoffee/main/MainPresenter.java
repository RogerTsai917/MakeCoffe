package com.roger.makecoffee.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.roger.makecoffee.R;
import com.roger.makecoffee.knowledge.KnowledgeFragment;
import com.roger.makecoffee.makecoffee.MakeCoffeeFragment;
import com.roger.makecoffee.news.NewsFragment;

public class MainPresenter implements MainContract.Presenter{
    private MainContract.View mMainView;
    private FragmentManager mChildFragmentManager;

    private NewsFragment mNewsFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private MakeCoffeeFragment mMakeCoffeeFragment;

    public MainPresenter(MainContract.View view, FragmentManager fragmentManager) {
        mMainView = view;
        mMainView.setPresenter(this);
        mChildFragmentManager = fragmentManager;
    }

    @Override
    public void transToNewsFragment() {
        mMainView.showNewsUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mNewsFragment == null) mNewsFragment = NewsFragment.newInstance();
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (!mNewsFragment.isAdded()) {
            transaction.add(R.id.frameLayout_main_container, mNewsFragment);
        } else {
            transaction.show(mNewsFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToCoffeeKnowledgeFragment() {
        mMainView.showCoffeeKnowledgeUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mKnowledgeFragment == null) mKnowledgeFragment = KnowledgeFragment.newInstance();
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (!mKnowledgeFragment.isAdded()) {
            transaction.add(R.id.frameLayout_main_container, mKnowledgeFragment);
        } else {
            transaction.show(mKnowledgeFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToMakeCoffeeFragment() {
        mMainView.showMakeCoffeeUi();

        FragmentTransaction transaction = mChildFragmentManager.beginTransaction();

        if (mMakeCoffeeFragment == null) mMakeCoffeeFragment = MakeCoffeeFragment.newInstance();
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (!mMakeCoffeeFragment.isAdded()) {
            transaction.add(R.id.frameLayout_main_container, mMakeCoffeeFragment);
        } else {
            transaction.show(mMakeCoffeeFragment);
        }
        transaction.commit();
    }

    @Override
    public void start() {
        transToNewsFragment();
    }
}
