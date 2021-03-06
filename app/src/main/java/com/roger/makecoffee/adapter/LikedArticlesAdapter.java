package com.roger.makecoffee.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roger.makecoffee.R;
import com.roger.makecoffee.liked.LikedFragment;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.LikedArticlesData;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.utils.Constants;

import java.text.SimpleDateFormat;


public class LikedArticlesAdapter extends RecyclerView.Adapter {
    private LikedFragment mFragment;

    public LikedArticlesAdapter(LikedFragment fragment) {
        mFragment = fragment;
        LikedArticlesData.getInstance();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_ARTICLES_LOADING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_liked_no_article, parent, false);
            return new LoadingNewsItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_liked_articles, parent, false);
            return new LikedArticlesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LikedArticlesViewHolder) {
            bindLikedArticlesViewHolder((LikedArticlesViewHolder) holder, position);
        }
    }

    private void bindLikedArticlesViewHolder(LikedArticlesViewHolder holder, int position) {
        final NewArticle article = LikedArticlesData.getInstance().getArticle(position);

        Glide.with(mFragment.getActivity())
                .load(article.getImageUrl())
                .into(holder.mPhotoImageView);

        holder.mTitleTextView.setText(article.getTitle());
        holder.mAuthorNameTextView.setText(article.getAuthor().getName());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = simpleDateFormat.format(article.getCreatedTime());
        holder.mTimeTextView.setText(stringDate);

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeCoffeeActivity)mFragment.getActivity()).transToArticleDetail(article);
            }
        });

        holder.mLikedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikedArticlesData.getInstance().removeLikedArticle(article.getArticleUid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return LikedArticlesData.getInstance().getLikedArticlesListSize() == 0
                ? 1 : LikedArticlesData.getInstance().getLikedArticlesListSize();
    }

    @Override
    public int getItemViewType(int position) {
        if (LikedArticlesData.getInstance().getLikedArticlesListSize() == 0) {
            return Constants.VIEW_TYPE_ARTICLES_LOADING;
        } else {
            return Constants.VIEW_TYPE_ARTICLES_LIST;
        }
    }

    private class LikedArticlesViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        ImageView mPhotoImageView;
        ImageView mLikedImageView;
        TextView mTitleTextView;
        TextView mAuthorNameTextView;
        TextView mTimeTextView;

        LikedArticlesViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_liked_articles_item);
            mPhotoImageView = itemView.findViewById(R.id.imageView_liked_articles_photo);
            mLikedImageView = itemView.findViewById(R.id.imageView_liked_articles_liked);
            mTitleTextView = itemView.findViewById(R.id.textView_liked_articles_title);
            mAuthorNameTextView = itemView.findViewById(R.id.textView_liked_articles_author_name);
            mTimeTextView = itemView.findViewById(R.id.textView_liked_articles_time);

            mPhotoImageView.setClipToOutline(true);
        }
    }

    private class LoadingNewsItemViewHolder extends RecyclerView.ViewHolder {

        LoadingNewsItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
