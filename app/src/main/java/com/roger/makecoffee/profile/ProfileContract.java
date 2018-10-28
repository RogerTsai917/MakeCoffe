package com.roger.makecoffee.profile;

import com.roger.makecoffee.BasePresenter;
import com.roger.makecoffee.BaseView;
import com.roger.makecoffee.objects.define.NewArticle;

import java.util.ArrayList;

public interface ProfileContract {

    interface View extends BaseView<Presenter> {
        void notifyAdapterDataSetChanged();
    }

    interface Presenter extends BasePresenter {
        void getArticlesDataFromFireStore(ArrayList<NewArticle> articleArrayList);
    }
}
