package com.roger.makecoffee.articleslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.ArticlesListAdapter;
import com.roger.makecoffee.decoration.ArticlesListDecoration;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.NewArticle;

import java.util.ArrayList;

public class ArticlesListFragment extends Fragment implements ArticlesListContract.View, View.OnClickListener {
    public static ArticlesListAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private ArticlesListContract.Presenter mPresenter;
    private ArrayList<NewArticle> mNewArticleArrayList;

    public ArticlesListFragment() {
        mNewArticleArrayList = new ArrayList<>();
    }

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        mPresenter = new ArticlesListPresenter(this);

        mRefreshLayout = view.findViewById(R.id.SwipeRefreshLayout_articles_list);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton_write_article);
        floatingActionButton.setOnClickListener(this);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_articles_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new ArticlesListAdapter(this, mPresenter, mNewArticleArrayList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.items_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mPresenter.isLoading()) {
                    getNewArticlesList();
                }
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

    @Override
    public void notifyAdapterDataSetChanged() {
        mRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();
    }

    public void getNewArticlesList() {
        mPresenter.getArticlesDataFromFireStore(mNewArticleArrayList);
    }

    public void transToArticleDetail(NewArticle newArticle) {
        ((MakeCoffeeActivity)getActivity()).transToArticleDetail(newArticle);
    }

    @Override
    public void setPresenter(ArticlesListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
