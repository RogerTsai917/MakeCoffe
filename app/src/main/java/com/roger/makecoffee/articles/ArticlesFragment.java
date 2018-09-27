package com.roger.makecoffee.articles;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;

public class ArticlesFragment extends Fragment {

    public ArticlesFragment() {
        // Requires empty public constructor
    }

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);


        return view;
    }
}
