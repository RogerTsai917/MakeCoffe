package com.roger.makecoffee.articledetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.ArticleDetailAdapter;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.NewArticle;

public class ArticleDetailFragment extends Fragment {
    private NewArticle mNewArticle;


    public ArticleDetailFragment() {

    }

    public static ArticleDetailFragment newInstance(NewArticle article) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setNewArticle(article);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MakeCoffeeActivity)getActivity()).hideToolbarAndNavBottom();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_article_detail);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ArticleDetailAdapter adapter = new ArticleDetailAdapter(this, mNewArticle);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MakeCoffeeActivity)getActivity()).showToolbarAndNavBottom();
    }

    private void setNewArticle(NewArticle newArticle) {
        mNewArticle = newArticle;
    }

}
