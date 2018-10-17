package com.roger.makecoffee.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roger.makecoffee.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticlesListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_articles_list, parent, false);
        return new ArticlesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindArticlesListViewHolder((ArticlesListViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return 5;//temp
    }

    private void bindArticlesListViewHolder(ArticlesListViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.mPhotoImageView.setImageResource(R.drawable.knowledge_makecoffee);
                break;
            case 1:
                holder.mPhotoImageView.setImageResource(R.drawable.knowledge_coffee_02);
                break;
            case 2:
                holder.mPhotoImageView.setImageResource(R.drawable.knowledge_makecoffee_espresso);
                break;
            case 3:
                holder.mPhotoImageView.setImageResource(R.drawable.knowledge_makecoffee_filter_coffee);
                break;
            case 4:
                holder.mPhotoImageView.setImageResource(R.drawable.knowledge_makecoffee_siphon);
                break;
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
