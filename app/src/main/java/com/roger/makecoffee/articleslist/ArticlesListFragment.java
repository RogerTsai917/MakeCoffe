package com.roger.makecoffee.articleslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;

public class ArticlesListFragment extends Fragment {

    public ArticlesListFragment() {

    }

    public static ArticlesListFragment newInstance() {
        return new ArticlesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);


        return view;
    }
}
