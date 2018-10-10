package com.roger.makecoffee.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roger.makecoffee.MakeCoffee;
import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.Article;
import com.roger.makecoffee.objects.define.ArticleStep;
import com.roger.makecoffee.objects.define.Author;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;
import com.roger.makecoffee.utils.ResizeBitmap;
import com.roger.makecoffee.writearticle.WriteArticleFragment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class WriteArticleDetailAdapter extends RecyclerView.Adapter {
    private WriteArticleFragment mFragment;

    private Article mArticle;

    public WriteArticleDetailAdapter(WriteArticleFragment fragment) {
        mFragment = fragment;

        initialArticle();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_WRITE_ARTICLE_TITLE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_title, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == Constants.VIEW_TYPE_WRITE_ARTICLE_ADD) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_add_step, parent, false);
            return new AddViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_step, parent, false);
            return new StepViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            try {
                bindTitleViewHolder((TitleViewHolder) holder, position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (holder instanceof StepViewHolder) {
            try {
                bindStepViewHolder((StepViewHolder) holder, position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (holder instanceof AddViewHolder) {
            bindAddViewHolder((AddViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mArticle.getArticleStepArrayListSize() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Constants.VIEW_TYPE_WRITE_ARTICLE_TITLE;
        } else if (position == mArticle.getArticleStepArrayListSize() + 1) {
            return Constants.VIEW_TYPE_WRITE_ARTICLE_ADD;
        } else {
            return Constants.VIEW_TYPE_WRITE_ARTICLE_STEP;
        }
    }

    private void bindTitleViewHolder(final TitleViewHolder holder, final int position) throws IOException {
        holder.mTitleEditText.setText(mArticle.getTitle());
        holder.mDescriptionEditText.setText(mArticle.getDescription());

        holder.mTitleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mArticle.setTitle(holder.mTitleEditText.getText().toString());
            }
        });

        holder.mDescriptionEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mArticle.setDescription(holder.mDescriptionEditText.getText().toString());
            }
        });

        holder.mUploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.getImageFromAlbum(position);

            }
        });

        if (!mArticle.getImageUrl().equals("")) {
            Uri uri = Uri.parse(mArticle.getImageUrl());
            Glide.with(mFragment)
                    .load(uri)
                    .into(holder.mPreviewImageView);
        }

    }

    private void bindStepViewHolder(final StepViewHolder holder, final int position) throws IOException {
        holder.setIsRecyclable(false);

        String stepCount = "Step "+ position + ":";
        final ArticleStep articleStep = mArticle.getArticleStepArrayList().get(position - 1);

        holder.mStepCounterTexView.setText(stepCount);
        holder.mContentEditText.setText(articleStep.getContent());

        holder.mContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                articleStep.setContent(holder.mContentEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        holder.mDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mArticle.getArticleStepArrayList().remove(position - 1);
              mFragment.notifyAdapterDataSetChanged();
            }
        });

        holder.mUploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.getImageFromAlbum(position);
            }
        });


        if (!articleStep.getPhotoUrl().equals("")) {
            Uri uri = Uri.parse(articleStep.getPhotoUrl());
            Glide.with(mFragment)
                    .load(uri)
                    .into(holder.mPhotoImageView);
            holder.mPhotoImageView.setVisibility(View.VISIBLE);
        } else {
            holder.mPhotoImageView.setVisibility(View.GONE);
        }
    }

    private void bindAddViewHolder(AddViewHolder holder, int position) {
        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewArticleStep();

            }
        });
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        EditText mTitleEditText;
        EditText mDescriptionEditText;
        ImageView mPreviewImageView;
        Button mUploadPhotoButton;

        public TitleViewHolder(View itemView) {
            super(itemView);
            mTitleEditText = itemView.findViewById(R.id.editText_write_article_title_title);
            mDescriptionEditText = itemView.findViewById(R.id.editText_write_article_title_description);
            mPreviewImageView = itemView.findViewById(R.id.imageView_write_article_title);
            mUploadPhotoButton = itemView.findViewById(R.id.button_write_article_title);
        }
    }

    private class StepViewHolder extends RecyclerView.ViewHolder {
        ImageView mDeleteImageView;
        TextView mStepCounterTexView;
        EditText mContentEditText;
        Button mUploadPhotoButton;
        ImageView mPhotoImageView;

        public StepViewHolder(View itemView) {
            super(itemView);
            mDeleteImageView = itemView.findViewById(R.id.imageView_write_article_step_delete);
            mStepCounterTexView = itemView.findViewById(R.id.textView_write_article_step_count);
            mContentEditText = itemView.findViewById(R.id.editText_write_article_step_content);
            mUploadPhotoButton = itemView.findViewById(R.id.button_write_article_step_upload);
            mPhotoImageView = itemView.findViewById(R.id.imageView_write_article_step_photo);
        }
    }

    private class AddViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;

        public AddViewHolder(View itemView) {
            super(itemView);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_write_article_add);
        }
    }

    private void initialArticle() {
        mArticle = new Article();
        mArticle.setTitle("");
        mArticle.setDescription("");
        mArticle.setImageUrl("");

        Author author = new Author();
        author.setName(UserManager.getInstance().getUserName());
        author.setEmail(UserManager.getInstance().getUserEmail());
        author.setImage(UserManager.getInstance().getUserPhotoUrl());
        author.setUid(UserManager.getInstance().getUserUid());
        mArticle.setAuthor(author);

        mArticle.addArticleStepArrayList(new ArticleStep("", ""));
    }

    private void addNewArticleStep() {
        if (!mArticle.getArticleStepArrayList().get(mArticle.getArticleStepArrayListSize() - 1)
                .getContent().equals("")) {
            mArticle.addArticleStepArrayList(new ArticleStep("", ""));
            mFragment.notifyAdapterDataSetChanged();
            mFragment.scrollTORecyclerViewBottom();
        } else {
            mFragment.showToast(MakeCoffee.getAppContext().getResources()
                    .getString(R.string.step_content_cannot_be_empty));
        }
    }

    public int getRecyclerViewSize() {
        return mArticle.getArticleStepArrayListSize() + 2;
    }

    public Article getArticle() {
        return mArticle;
    }


}
