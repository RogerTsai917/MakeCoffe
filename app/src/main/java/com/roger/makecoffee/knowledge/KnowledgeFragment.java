package com.roger.makecoffee.knowledge;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;

public class KnowledgeFragment extends Fragment implements KnowledgeContract.View {
    private KnowledgeContract.Presenter mPresenter;

    public KnowledgeFragment() {

    }

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

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
