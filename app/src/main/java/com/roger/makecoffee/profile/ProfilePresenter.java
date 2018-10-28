package com.roger.makecoffee.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View mView;
    private FirebaseFirestore mDb;

    ProfilePresenter(ProfileContract.View view) {
        mView = view;
        mDb = FirebaseFirestore.getInstance();
    }

    @Override
    public void start() {

    }


    @Override
    public void getArticlesDataFromFireStore(final ArrayList<NewArticle> articleArrayList) {
        articleArrayList.clear();

        mDb.collection(Constants.USERS)
                .document(UserManager.getInstance().getUserUid())
                .collection(Constants.POSTED_ARTICLES)
                .orderBy(Constants.CREATE_TIME, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                NewArticle article = document.toObject(NewArticle.class);
                                articleArrayList.add(article);
                            }
                            Log.d(Constants.TAG, "onComplete, HistoryArticleList = " + articleArrayList.size());
                            mView.notifyAdapterDataSetChanged();
                        }
                    }
                });
    }
}
