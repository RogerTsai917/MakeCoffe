package com.roger.makecoffee.makecoffeeactivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.roger.makecoffee.R;
import com.roger.makecoffee.articledetail.ArticleDetailFragment;
import com.roger.makecoffee.articleslist.ArticlesListFragment;
import com.roger.makecoffee.knowledge.KnowledgeFragment;
import com.roger.makecoffee.knowledgedetail.KnowledgeDetailFragment;
import com.roger.makecoffee.liked.LikedFragment;
import com.roger.makecoffee.makecoffee.MakeCoffeeFragment;
import com.roger.makecoffee.makecoffeedetail.MakeCoffeeDetailFragment;
import com.roger.makecoffee.news.NewsFragment;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.profile.ProfileFragment;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.writearticle.WriteArticleFragment;

public class MakeCoffeePresenter implements MakeCoffeeContract.Presenter {
    private static final String MAKE_COFFEE = "MAKE_COFFEE";
    private static final String KNOWLEDGE = "KNOWLEDGE";
    private static final String NEWS = "NEWS";
    private static final String ARTICLES_LIST = "ARTICLES_LIST";
    private static final String LIKED = "LIKED";
    private static final String PROFILE = "PROFILE";
    private static final String MAKE_COFFEE_DETAIL = "MAKE_COFFEE_DETAIL";
    private static final String KNOWLEDGE_DETAIL = "KNOWLEDGE_DETAIL";
    private static final String WRITE_ARTICLE = "WRITE_ARTICLE";
    private static final String ARTICLE_DETAIL = "ARTICLE_DETAIL";

    private MakeCoffeeContract.View mMakeCoffeeView;
    private FragmentManager mFragmentManager;

    private MakeCoffeeFragment mMakeCoffeeFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NewsFragment mNewsFragment;
    private ArticlesListFragment mArticlesListFragment;
    private LikedFragment mLikedFragment;
    private ProfileFragment mProfileFragment;

    public MakeCoffeePresenter(MakeCoffeeContract.View makeCoffeeView, FragmentManager fragmentManager) {
        mMakeCoffeeView = makeCoffeeView;
        makeCoffeeView.setPresenter(this);
        mFragmentManager = fragmentManager;
    }


    @Override
    public void transToMakeCoffee() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();

        if (mMakeCoffeeFragment == null) mMakeCoffeeFragment = MakeCoffeeFragment.newInstance();
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mMakeCoffeeFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mMakeCoffeeFragment, MAKE_COFFEE);
        } else {
            transaction.show(mMakeCoffeeFragment);
        }

        transaction.commit();
    }

    @Override
    public void transToKnowledge() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();


        if (mKnowledgeFragment == null) mKnowledgeFragment = KnowledgeFragment.newInstance();
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mKnowledgeFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mKnowledgeFragment, KNOWLEDGE);
        } else {
            transaction.show(mKnowledgeFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToNews() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();

        if (mNewsFragment == null) mNewsFragment = NewsFragment.newInstance();
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mNewsFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mNewsFragment, NEWS);
        } else {
            transaction.show(mNewsFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToArticles() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();

        if (mArticlesListFragment == null) mArticlesListFragment = ArticlesListFragment.newInstance();
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mArticlesListFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mArticlesListFragment, ARTICLES_LIST);
        } else {
            transaction.show(mArticlesListFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToLiked() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();


        if (mLikedFragment == null) mLikedFragment = LikedFragment.newInstance();
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mProfileFragment != null) transaction.hide(mProfileFragment);
        if (!mLikedFragment.isAdded()) {
            transaction.add(R.id.frameLayout_make_coffee_container, mLikedFragment, LIKED);
        } else {
            transaction.show(mLikedFragment);
        }
        transaction.commit();
    }

    @Override
    public void transToProfile() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(KNOWLEDGE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(MAKE_COFFEE_DETAIL) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(WRITE_ARTICLE) != null) mFragmentManager.popBackStack();
        if (mFragmentManager.findFragmentByTag(ARTICLE_DETAIL) != null) mFragmentManager.popBackStack();

        if (mProfileFragment == null) mProfileFragment = ProfileFragment.newInstance();
        if (mMakeCoffeeFragment != null) transaction.hide(mMakeCoffeeFragment);
        if (mKnowledgeFragment != null) transaction.hide(mKnowledgeFragment);
        if (mNewsFragment != null) transaction.hide(mNewsFragment);
        if (mArticlesListFragment != null) transaction.hide(mArticlesListFragment);
        if (mLikedFragment != null) transaction.hide(mLikedFragment);
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

        hideFragment(transaction);

        MakeCoffeeDetailFragment makeCoffeeDetailFragment = MakeCoffeeDetailFragment.newInstance(teaching);
        transaction.add(R.id.frameLayout_make_coffee_container, makeCoffeeDetailFragment, MAKE_COFFEE_DETAIL);
        transaction.commit();
    }

    @Override
    public void transToKnowledgeDetail(CoffeeKnowledgeCollection collection) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        hideFragment(transaction);

        KnowledgeDetailFragment knowledgeDetailFragment = KnowledgeDetailFragment.newInstance(collection);
        transaction.add(R.id.frameLayout_make_coffee_container, knowledgeDetailFragment, KNOWLEDGE_DETAIL);
        transaction.commit();
    }

    @Override
    public void transToWriteArticle() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        hideFragment(transaction);

        WriteArticleFragment writeArticleFragment = WriteArticleFragment.newInstance();
        transaction.add(R.id.frameLayout_make_coffee_container, writeArticleFragment, WRITE_ARTICLE);
        transaction.commit();
    }

    @Override
    public void transToArticleDetail(NewArticle newArticle) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        hideFragment(transaction);

        ArticleDetailFragment articleDetailFragment = ArticleDetailFragment.newInstance(newArticle);
        transaction.add(R.id.frameLayout_make_coffee_container, articleDetailFragment, ARTICLE_DETAIL);
        transaction.commit();
    }

    @Override
    public void start() {
        transToMakeCoffee();
    }

    @Override
    public void setToolbarTitle(String title) {
        mMakeCoffeeView.setToolbarTitle(title);
    }

    @Override
    public void logout() {
        UserManager.getInstance().signOut(mMakeCoffeeView.getMakeCoffeeActivity());
    }


    private void hideFragment(FragmentTransaction transaction) {
        if (mMakeCoffeeFragment != null && !mMakeCoffeeFragment.isHidden()) {
            transaction.hide(mMakeCoffeeFragment);
            transaction.addToBackStack(MAKE_COFFEE);
        }
        if (mKnowledgeFragment != null && !mKnowledgeFragment.isHidden()) {
            transaction.hide(mKnowledgeFragment);
            transaction.addToBackStack(KNOWLEDGE);
        }
        if (mNewsFragment != null && !mNewsFragment.isHidden()) {
            transaction.hide(mNewsFragment);
            transaction.addToBackStack(NEWS);
        }
        if (mArticlesListFragment != null && !mArticlesListFragment.isHidden()) {
            transaction.hide(mArticlesListFragment);
            transaction.addToBackStack(ARTICLES_LIST);
        }
        if (mLikedFragment != null && !mLikedFragment.isHidden()) {
            transaction.hide(mLikedFragment);
            transaction.addToBackStack(LIKED);
        }

        if (mProfileFragment != null && !mProfileFragment.isHidden()) {
            transaction.hide(mProfileFragment);
            transaction.addToBackStack(PROFILE);
        }
    }
}
