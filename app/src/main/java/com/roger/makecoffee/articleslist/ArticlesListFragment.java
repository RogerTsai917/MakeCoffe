package com.roger.makecoffee.articleslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.ArticlesListAdapter;
import com.roger.makecoffee.decoration.ArticlesListDecoration;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;

public class ArticlesListFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ArticlesListAdapter mAdapter;
    private FloatingActionButton mFloatingActionButton;

    public ArticlesListFragment() {

    }

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_write_article);
        mFloatingActionButton.setOnClickListener(this);

        mRecyclerView = view.findViewById(R.id.recyclerView_articles_list);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ArticlesListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.make_coffee_item_space)));

        return view;
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
}
