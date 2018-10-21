package com.roger.makecoffee.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.roger.makecoffee.R;
import com.roger.makecoffee.articleslist.ArticlesListFragment;
import com.roger.makecoffee.objects.LikedArticlesData;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticlesListAdapter extends RecyclerView.Adapter {
    private ArticlesListFragment mFragment;
    private ArrayList<NewArticle> mNewArticleArrayList;
    private FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private String mUserUid;

    public ArticlesListAdapter(ArticlesListFragment fragment) {
        mFragment = fragment;
        mNewArticleArrayList = new ArrayList<>();
        LikedArticlesData.getInstance();
        getArticlesDataFromFireStore();
        mUserUid = UserManager.getInstance().getUserUid();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_ARTICLES_LOADING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news_loading, parent, false);
            return new LoadingNewsItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_articles_list, parent, false);
            return new ArticlesListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticlesListViewHolder) {
            bindArticlesListViewHolder((ArticlesListViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mNewArticleArrayList.size() == 0 ? 1 : mNewArticleArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mNewArticleArrayList.size() == 0) {
            return Constants.VIEW_TYPE_ARTICLES_LOADING;
        }
        else {
            return Constants.VIEW_TYPE_ARTICLES_LIST;
        }
    }

    private void bindArticlesListViewHolder(ArticlesListViewHolder holder, int position) {
        final NewArticle article = mNewArticleArrayList.get(position);
        holder.mTitleTextView.setText(article.getTitle());
        holder.mAuthorNameTextView.setText(article.getAuthor().getName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = simpleDateFormat.format(article.getCreatedTime());
        holder.mTimeTextView.setText(stringDate);

        if (article.getAuthor().getUid().equals(mUserUid)) {
            holder.mSettingImageView.setVisibility(View.GONE);
            holder.mLikedImageView.setVisibility(View.GONE);
        } else {
            holder.mSettingImageView.setVisibility(View.GONE);
            holder.mLikedImageView.setVisibility(View.VISIBLE);
        }

        if (LikedArticlesData.getInstance().isLikedArticle(article.getArticleUid())) {
            holder.mLikedImageView.setImageResource(R.drawable.btn_like_selected);

        } else {
            holder.mLikedImageView.setImageResource(R.drawable.btn_like_normal);
        }

        holder.mLikedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LikedArticlesData.getInstance().isLikedArticle(article.getArticleUid())) {
                    LikedArticlesData.getInstance().removeLikedArticle(article.getArticleUid());
                } else {
                    LikedArticlesData.getInstance().addLikedArticle(article.getArticleUid());
                }
            }
        });

        Glide.with(mFragment)
                .load(article.getImageUrl())
                .into(holder.mPhotoImageView);
        Glide.with(mFragment)
                .load(article.getAuthor().getImage())
                .into(holder.mAuthorPhotoCircleImageView);

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.transToArticleDetail(article);
            }
        });
    }

    public void getArticlesDataFromFireStore() {
        mNewArticleArrayList.clear();

        mDb.collection(Constants.ARTICLES).orderBy(Constants.CREATE_TIME, Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewArticle article = document.toObject(NewArticle.class);
                                mNewArticleArrayList.add(article);
                            }
                            Log.d(Constants.TAG, "onComplete, ArticleArrayList = " + mNewArticleArrayList.size());
                            mFragment.notifyAdapterDataSetChanged();
                        }
                    }
                });
    }

    private class LoadingNewsItemViewHolder extends RecyclerView.ViewHolder {

        public LoadingNewsItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ArticlesListViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        CircleImageView mAuthorPhotoCircleImageView;
        ImageView mLikedImageView;
        ImageView mSettingImageView;
        ImageView mPhotoImageView;
        TextView mAuthorNameTextView;
        TextView mTimeTextView;
        TextView mTitleTextView;

        public ArticlesListViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_articles_list);
            mAuthorPhotoCircleImageView = itemView.findViewById(R.id.circleImageView_articles_ist);
            mLikedImageView = itemView.findViewById(R.id.imageView_articles_list_like);
            mSettingImageView = itemView.findViewById(R.id.imageView_articles_list_setting);
            mPhotoImageView = itemView.findViewById(R.id.imageView_articles_list_photo);
            mAuthorNameTextView = itemView.findViewById(R.id.textView_articles_list_name);
            mTimeTextView = itemView.findViewById(R.id.textView_articles_list_time);
            mTitleTextView = itemView.findViewById(R.id.textView_articles_list_title);

            mConstraintLayout.setClipToOutline(true);
        }
    }
}
