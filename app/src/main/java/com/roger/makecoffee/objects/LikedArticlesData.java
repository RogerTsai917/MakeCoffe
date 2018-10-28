package com.roger.makecoffee.objects;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.roger.makecoffee.articleslist.ArticlesListFragment;
import com.roger.makecoffee.liked.LikedFragment;
import com.roger.makecoffee.objects.define.LikedArticle;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import java.util.ArrayList;

public class LikedArticlesData {
    private static LikedArticlesData mLikedArticlesData;
    private FirebaseFirestore mDb;
    private ArrayList<LikedArticle> mLikedArticlesList;
    private ArrayList<NewArticle> mArticlesList;

    private LikedArticlesData() {
        mDb = FirebaseFirestore.getInstance();
        mLikedArticlesList = new ArrayList<>();
        mArticlesList = new ArrayList<>();
        getUserLikedArticlesList();
    }

    public static synchronized LikedArticlesData getInstance() {
        if (mLikedArticlesData == null) {
            mLikedArticlesData = new LikedArticlesData();
        }
        return mLikedArticlesData;
    }

    private void getUserLikedArticlesList() {
        mLikedArticlesList.clear();

        mDb.collection(Constants.USERS).document(UserManager.getInstance().getUserUid())
                .collection(Constants.LIKED_ARTICLES)
                .orderBy(Constants.ADD_TIME, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                LikedArticle likedArticle = document.toObject(LikedArticle.class);
                                mLikedArticlesList.add(likedArticle);
                            }
                            Log.d(Constants.TAG, "onComplete, ArticleArrayList = " + mLikedArticlesList.size());
                            getArticlesList();
                        }
                    }
                });
    }

    public void getArticlesList() {
        for (LikedArticle likedArticle : mLikedArticlesList) {
            addArticlesFromFireStore(likedArticle);
        }
        notifyAdapterDataSetChanged();
    }

    public void addArticlesFromFireStore(LikedArticle likedArticle) {
        mDb.collection(Constants.ARTICLES).document(likedArticle.getArticleUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                NewArticle newArticle = document.toObject(NewArticle.class);
                                mArticlesList.add(0, newArticle);
                            }
                        }
                    }
                });
    }

    public NewArticle getArticle(int position) {
        return mArticlesList.get(position);
    }


    public void removeLikedArticle(String articleId) {
        int position = getItemPositionInLikedArticlesList(articleId);

        if (position != -1) {
            removeLikedArticleFromFireStore(articleId);
            mLikedArticlesList.remove(position);
            mArticlesList.remove(position);
            notifyAdapterDataSetChanged();
        }
    }

    public boolean isLikedArticle(String articleId) {
        for (int i = 0; i < mLikedArticlesList.size(); i++) {
            if (mLikedArticlesList.get(i).getArticleUid().equals(articleId)) {
                return true;
            }
        }
        return false;
    }

    public void addLikedArticle(String articleId) {
        LikedArticle likedArticle = new LikedArticle();
        likedArticle.setArticleUid(articleId);

        mLikedArticlesList.add(0,likedArticle);
        addArticlesFromFireStore(likedArticle);

        mDb.collection(Constants.USERS)
                .document(UserManager.getInstance().getUserUid())
                .collection(Constants.LIKED_ARTICLES)
                .document(articleId)
                .set(likedArticle);

        notifyAdapterDataSetChanged();
    }

    private int getItemPositionInLikedArticlesList(String articleId) {
        for (int i = 0; i < mLikedArticlesList.size(); i++) {
            if (mLikedArticlesList.get(i).getArticleUid().equals(articleId)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<LikedArticle> getLikedArticlesList() {
        return mLikedArticlesList;
    }

    public int getLikedArticlesListSize() {
        return mArticlesList.size();
    }

    private void notifyAdapterDataSetChanged() {
        if (ArticlesListFragment.mAdapter != null) {
            ArticlesListFragment.mAdapter.notifyDataSetChanged();
        }
        if (LikedFragment.mAdapter != null) {
            LikedFragment.mAdapter.notifyDataSetChanged();
        }
    }

    private void removeLikedArticleFromFireStore(String articleId) {
        mDb.collection(Constants.USERS).document(UserManager.getInstance().getUserUid())
                .collection(Constants.LIKED_ARTICLES).document(articleId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(Constants.TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(Constants.TAG, "Error deleting document", e);
            }
        });
    }
}
