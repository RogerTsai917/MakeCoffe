package com.roger.makecoffee.knowledge;

public class KnowledgePresenter implements KnowledgeContract.Presenter {
    private KnowledgeContract.View mKnowledgeView;

    public KnowledgePresenter(KnowledgeContract.View view) {
        mKnowledgeView = view;
        mKnowledgeView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
