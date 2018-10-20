package com.roger.makecoffee.articleslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.MakeCoffee;
import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.ArticlesListAdapter;
import com.roger.makecoffee.decoration.ArticlesListDecoration;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.Article;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.utils.Constants;

import java.util.ArrayList;

public class ArticlesListFragment extends Fragment implements ArticlesListContract.View, View.OnClickListener {
    private static ArticlesListFragment mArticlesListFragment;
    private RecyclerView mRecyclerView;
    private ArticlesListAdapter mAdapter;
    private FloatingActionButton mFloatingActionButton;
    private SwipeRefreshLayout mRefreshLayout;
    private ArticlesListContract.Presenter mPresenter;

    public ArticlesListFragment() {

    }

    public static ArticlesListFragment newInstance() {
        if (mArticlesListFragment == null) {
            mArticlesListFragment = new ArticlesListFragment();
        }
        return mArticlesListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        mRefreshLayout = view.findViewById(R.id.SwipeRefreshLayout_articles_list);
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_write_article);
        mFloatingActionButton.setOnClickListener(this);

        mRecyclerView = view.findViewById(R.id.recyclerView_articles_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ArticlesListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.make_coffee_item_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new ArticlesListPresenter(this);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewArticlesList();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton_write_article:
                ((MakeCoffeeActivity)getActivity()).transToWriteArticle();
                break;

            default:
        }
    }

    public void notifyAdapterDataSetChanged() {
        mRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    public void getNewArticlesList() {
        mAdapter.getArticlesDataFromFireStore();
    }

    public void transToArticleDetail(NewArticle newArticle) {
        ((MakeCoffeeActivity)getActivity()).transToArticleDetail(newArticle);
    }

    @Override
    public void setPresenter(ArticlesListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
