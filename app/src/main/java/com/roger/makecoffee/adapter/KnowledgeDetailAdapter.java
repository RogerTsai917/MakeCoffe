package com.roger.makecoffee.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roger.makecoffee.R;
import com.roger.makecoffee.knowledgedetail.KnowledgeDetailFragment;
import com.roger.makecoffee.objects.define.CoffeeKnowledge;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;

public class KnowledgeDetailAdapter extends RecyclerView.Adapter {
    private KnowledgeDetailFragment mFragment;
    private CoffeeKnowledgeCollection mCollection;

    public KnowledgeDetailAdapter(KnowledgeDetailFragment fragment, CoffeeKnowledgeCollection collection) {
        mFragment = fragment;
        mCollection = collection;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_knowledge_detail, parent, false);
        return new KnowledgeDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindKnowledgeDetailViewHolder((KnowledgeDetailViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return mCollection.getCoffeeKnowledgeArrayList().size();
    }

    private void bindKnowledgeDetailViewHolder(KnowledgeDetailViewHolder holder,int position) {
        final CoffeeKnowledge knowledge = mCollection.getCoffeeKnowledgeArrayList().get(position);

        holder.mImageView.setImageResource(knowledge.getImage());

        holder.mTextView.setText(knowledge.getName());

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(knowledge);
            }
        });
    }

    private void showDialog(CoffeeKnowledge knowledge) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mFragment.getContext());
        AlertDialog dialog = builder.create();
        View dialogView = View.inflate(mFragment.getContext(), R.layout.dialog_knowledge_detail, null);

        ImageView imageView = dialogView.findViewById(R.id.imageView_knowledge_detail_dialog);
        TextView textView = dialogView.findViewById(R.id.textView_knowledge_detail_dialog);

        imageView.setImageResource(knowledge.getImage());
        textView.setText(knowledge.getContent());

        dialog.setView(dialogView);
        dialog.show();
    }

    private class KnowledgeDetailViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        ImageView mImageView;
        TextView mTextView;

        KnowledgeDetailViewHolder(View itemView) {
            super(itemView);

            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_knowledge_detail);
            mImageView = itemView.findViewById(R.id.imageView_knowledge_detail);
            mTextView = itemView.findViewById(R.id.textView_knowledge_detail);

            mImageView.setClipToOutline(true);
        }
    }
}
