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
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.profile.ProfileFragment;
import com.roger.makecoffee.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryArticlesAdapter extends RecyclerView.Adapter {
    private ProfileFragment mFragment;
    private ArrayList<NewArticle> mArticlesList;

    public HistoryArticlesAdapter(ProfileFragment fragment, ArrayList<NewArticle> articleArrayList) {
        mFragment = fragment;
        mArticlesList = articleArrayList;
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
                    .inflate(R.layout.item_history_article, parent, false);
            return new HistoryArticlesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HistoryArticlesViewHolder) {
            bindHistoryArticlesViewHolder((HistoryArticlesViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mArticlesList.size() == 0 ? 1 : mArticlesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mArticlesList.size() == 0) {
            return Constants.VIEW_TYPE_ARTICLES_LOADING;
        } else {
            return Constants.VIEW_TYPE_ARTICLES_LIST;
        }
    }

    private void bindHistoryArticlesViewHolder(HistoryArticlesViewHolder holder, int position) {
        final NewArticle article = mArticlesList.get(position);

        Glide.with(mFragment.getActivity())
                .load(article.getImageUrl())
                .into(holder.mPhotoImageView);

        holder.mTitle.setText(article.getTitle());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = simpleDateFormat.format(article.getCreatedTime());
        holder.mTime.setText(stringDate);

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeCoffeeActivity)mFragment.getActivity()).transToArticleDetail(article);
            }
        });
    }

    private class HistoryArticlesViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        ImageView mPhotoImageView;
        TextView mTitle;
        TextView mTime;

        HistoryArticlesViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_history_article_item);
            mPhotoImageView = itemView.findViewById(R.id.imageView_history_article_photo);
            mTitle = itemView.findViewById(R.id.textView_history_articles_title);
            mTime = itemView.findViewById(R.id.textView_history_article_time);

            mPhotoImageView.setClipToOutline(true);
        }
    }

    private class LoadingNewsItemViewHolder extends RecyclerView.ViewHolder {

        LoadingNewsItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
