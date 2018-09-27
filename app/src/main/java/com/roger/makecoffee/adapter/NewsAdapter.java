package com.roger.makecoffee.adapter;

import android.app.Fragment;
import android.support.annotation.NonNull;
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
        if (viewType == Constants.VIEW_TYPE_NEWS_BIG) {
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
        } else {
            bindSmallNewsItemViewHolder((SmallNewsItemViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return NewsData.getInstance().getNewsDataSize();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? Constants.VIEW_TYPE_NEWS_BIG : Constants.VIEW_TYPE_NEWS_SMALL;
    }

    private void bindBigNewsItemViewHolder(BigNewsItemViewHolder holder, int position) {
        News news = NewsData.getInstance().getNewsArrayList().get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsContent.setText(news.getContent());

        Glide.with(mFragment)
                .load(news.getUrlToImage())
                .into(holder.newsImageView);

    }

    private void bindSmallNewsItemViewHolder(SmallNewsItemViewHolder holder, int position) {
        News news = NewsData.getInstance().getNewsArrayList().get(position);
        holder.newsTitle.setText(news.getTitle());
        Glide.with(mFragment)
                .load(news.getUrlToImage())
                .into(holder.newsImageView);
    }

    private class BigNewsItemViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImageView;
        TextView newsTitle;
        TextView newsContent;

        public BigNewsItemViewHolder(View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.imageView_bigger_news_item_image);
            newsTitle = itemView.findViewById(R.id.textView_smaller_news_item_title);
            newsContent = itemView.findViewById(R.id.textView_bigger_news_item_content);
        }
    }

    private class SmallNewsItemViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImageView;
        TextView newsTitle;

        public SmallNewsItemViewHolder(View itemView) {
            super(itemView);
            newsImageView = itemView.findViewById(R.id.imageView_smaller_news_item_image);
            newsTitle = itemView.findViewById(R.id.textView_smaller_news_item_title);
        }
    }
}
