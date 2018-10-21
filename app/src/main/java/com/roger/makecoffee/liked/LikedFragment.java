package com.roger.makecoffee.liked;

import android.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.ArticlesListAdapter;
import com.roger.makecoffee.adapter.LikedArticlesAdapter;
import com.roger.makecoffee.decoration.ArticlesListDecoration;


public class LikedFragment extends Fragment {
    private RecyclerView mRecyclerView;
    public static LikedArticlesAdapter mAdapter;

    public LikedFragment() {

    }

    public static LikedFragment newInstance() {
        return new LikedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liked, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView_liked_articles);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new LikedArticlesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.make_coffee_item_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
