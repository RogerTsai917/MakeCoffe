package com.roger.makecoffee.knowledge;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;

public interface KnowledgeContract {

    interface View extends BaseView<Presenter> {
        void showKnowledgeDetail();
    }

    interface Presenter extends BasePresenter {

    }
}
