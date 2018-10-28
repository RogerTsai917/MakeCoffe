package com.roger.makecoffee.articleslist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ArticlesListPresenter implements ArticlesListContract.Presenter {
    private ArticlesListContract.View mView;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();


    ArticlesListPresenter(ArticlesListContract.View view) {
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void getArticlesDataFromFireStore(final ArrayList<NewArticle> articleArrayList) {
        articleArrayList.clear();

        mDb.collection(Constants.ARTICLES).orderBy(Constants.CREATE_TIME, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                NewArticle article = document.toObject(NewArticle.class);
                                articleArrayList.add(article);
                            }
                            Log.d(Constants.TAG, "onComplete, ArticleArrayList = " + articleArrayList.size());
                            mView.notifyAdapterDataSetChanged();
                        }
                    }
                });
    }
}
