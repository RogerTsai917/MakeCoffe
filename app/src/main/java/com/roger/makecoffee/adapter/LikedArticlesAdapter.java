package com.roger.makecoffee.adapter;

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


public class LikedArticlesAdapter extends RecyclerView.Adapter {
    private LikedFragment mFragment;

    public LikedArticlesAdapter(LikedFragment fragment) {
        mFragment = fragment;
        LikedArticlesData.getInstance();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liked_articles, parent, false);
        return new LikedArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindLikedArticlesViewHolder((LikedArticlesViewHolder) holder, position);
    }

    private void bindLikedArticlesViewHolder(LikedArticlesViewHolder holder, int position) {
        final NewArticle article = LikedArticlesData.getInstance().getArticle(position);

        Glide.with(mFragment)
                .load(article.getImageUrl())
                .into(holder.mPhotoImageView);

        holder.mTitleTextView.setText(article.getTitle());
        holder.mAuthorNameTextView.setText(article.getAuthor().getName());

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeCoffeeActivity)mFragment.getActivity()).transToArticleDetail(article);
            }
        });

    }

    @Override
    public int getItemCount() {
        return LikedArticlesData.getInstance().getLikedArticlesListSize();
    }

    private class LikedArticlesViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        ImageView mPhotoImageView;
        ImageView mLikedImageView;
        TextView mTitleTextView;
        TextView mAuthorNameTextView;

        public LikedArticlesViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_liked_articles_item);
            mPhotoImageView = itemView.findViewById(R.id.imageView_liked_articles_photo);
            mLikedImageView = itemView.findViewById(R.id.imageView_liked_articles_liked);
            mTitleTextView = itemView.findViewById(R.id.textView_liked_articles_title);
            mAuthorNameTextView = itemView.findViewById(R.id.textView_liked_articles_author_name);

            mPhotoImageView.setClipToOutline(true);
        }
    }
}
