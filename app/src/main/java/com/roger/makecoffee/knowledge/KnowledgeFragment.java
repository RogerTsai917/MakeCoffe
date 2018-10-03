package com.roger.makecoffee.knowledge;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.KnowledgeAdapter;
import com.roger.makecoffee.decoration.KnowledgeItemDecoration;

public class KnowledgeFragment extends Fragment implements KnowledgeContract.View {
    private KnowledgeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private KnowledgeAdapter mAdapter;

    public KnowledgeFragment() {

    }

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView_knowledge);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(), 2);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new KnowledgeAdapter(this);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new KnowledgeItemDecoration(getResources().getDimensionPixelSize(R.dimen.knowledge_item_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new KnowledgePresenter(this);
        mPresenter.start();
    }

    @Override
    public void showKnowledgeDetail() {

    }

    @Override
    public void setPresenter(KnowledgeContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
