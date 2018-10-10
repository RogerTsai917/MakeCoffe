package com.roger.makecoffee.articleslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;

public class ArticlesListFragment extends Fragment implements View.OnClickListener {
    RecyclerView mRecyclerView;
    FloatingActionButton mFloatingActionButton;

    public ArticlesListFragment() {

    }

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView_articles_list);
        mFloatingActionButton = view.findViewById(R.id.floatingActionButton_write_article);
        mFloatingActionButton.setOnClickListener(this);

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
