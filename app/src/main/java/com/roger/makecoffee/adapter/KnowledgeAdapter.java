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
import com.roger.makecoffee.knowledge.KnowledgeFragment;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.KnowledgeData;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;

public class KnowledgeAdapter extends RecyclerView.Adapter {
    private KnowledgeFragment mFragment;

    public KnowledgeAdapter(KnowledgeFragment fragment) {
        mFragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_knowledge, parent, false);
        return new KnowledgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindKnowledgeViewHolder((KnowledgeViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return KnowledgeData.getInstance().getKnowledgeDataSize();
    }

    private void bindKnowledgeViewHolder(KnowledgeViewHolder holder, int position) {
        final CoffeeKnowledgeCollection collection
                = KnowledgeData.getInstance().getKnowledgeCollectionArrayList().get(position);
        holder.knowledgeImageView.setImageResource(collection.getDrawableId());
        holder.knowledgeTExtView.setText(collection.getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeCoffeeActivity)mFragment.getActivity()).transToKnowledgeDetail(collection);
            }
        });
    }

    private class KnowledgeViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView knowledgeImageView;
        TextView knowledgeTExtView;

        KnowledgeViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout_knowledge_item);
            knowledgeImageView = itemView.findViewById(R.id.imageView_knowledge);
            knowledgeTExtView = itemView.findViewById(R.id.textView_knowledge);

            knowledgeImageView.setClipToOutline(true);
        }
    }

}
