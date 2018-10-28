package com.roger.makecoffee.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.NewsAdapter;

public class NewsFragment extends Fragment implements NewsContract.View {
    private NewsAdapter mNewsAdapter;
    private NewsContract.Presenter mPresenter;

    public NewsFragment() {

    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_news);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        mNewsAdapter = new NewsAdapter(this);

        recyclerView.setAdapter(mNewsAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new NewsPresenter(this);
        mPresenter.start();
    }

    @Override
    public void showNews() {
        mNewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNewsDetail() {

    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
