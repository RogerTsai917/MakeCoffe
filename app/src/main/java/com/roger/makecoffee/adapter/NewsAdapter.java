package com.roger.makecoffee.adapter;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
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
import com.roger.makecoffee.objects.NewsData;
import com.roger.makecoffee.objects.define.News;
import com.roger.makecoffee.utils.Constants;

public class NewsAdapter extends RecyclerView.Adapter {
    private Fragment mFragment;

    public NewsAdapter(Fragment fragment) {
        mFragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_NEWS_LOADING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news_loading, parent, false);
            return new LoadingNewsItemViewHolder(view);
        } else if (viewType == Constants.VIEW_TYPE_NEWS_BIG) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news_big_picture, parent, false);
            return new BigNewsItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news_small_picture, parent, false);
            return new SmallNewsItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BigNewsItemViewHolder) {
            bindBigNewsItemViewHolder((BigNewsItemViewHolder) holder, position);
        } else if (holder instanceof SmallNewsItemViewHolder) {
            bindSmallNewsItemViewHolder((SmallNewsItemViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return NewsData.getInstance().getNewsDataSize() == 0 ? 1 : NewsData.getInstance().getNewsDataSize();
    }

    @Override
    public int getItemViewType(int position) {
        if (NewsData.getInstance().getNewsDataSize() == 0) {
            return Constants.VIEW_TYPE_NEWS_LOADING;
        } else {
            return position == 0 ? Constants.VIEW_TYPE_NEWS_BIG : Constants.VIEW_TYPE_NEWS_SMALL;
        }
    }

    private void bindBigNewsItemViewHolder(BigNewsItemViewHolder holder, int position) {
        final News news = NewsData.getInstance().getNewsArrayList().get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsContent.setText(news.getContent());

        Glide.with(mFragment)
                .load(news.getUrlToImage())
                .into(holder.newsImageView);

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWebView(news.getUrl());
            }
        });

    }

    private void bindSmallNewsItemViewHolder(SmallNewsItemViewHolder holder, int position) {
        final News news = NewsData.getInstance().getNewsArrayList().get(position);
        holder.newsTitle.setText(news.getTitle());
        Glide.with(mFragment)
                .load(news.getUrlToImage())
                .into(holder.newsImageView);

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWebView(news.getUrl());
            }
        });
    }

    private void onWebView(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mFragment.getActivity().startActivity(intent);
    }

    private class LoadingNewsItemViewHolder extends RecyclerView.ViewHolder {

        public LoadingNewsItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class BigNewsItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mLayout;
        ImageView newsImageView;
        TextView newsTitle;
        TextView newsContent;

        public BigNewsItemViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.constraintLayout_bigger_news_item);
            newsImageView = itemView.findViewById(R.id.imageView_bigger_news_item_image);
            newsTitle = itemView.findViewById(R.id.textView_smaller_news_item_title);
            newsContent = itemView.findViewById(R.id.textView_bigger_news_item_content);
        }
    }

    private class SmallNewsItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mLayout;
        ImageView newsImageView;
        TextView newsTitle;

        public SmallNewsItemViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.constraintLayout_smaller_news_item);
            newsImageView = itemView.findViewById(R.id.imageView_smaller_news_item_image);
            newsTitle = itemView.findViewById(R.id.textView_smaller_news_item_title);
        }
    }
}
