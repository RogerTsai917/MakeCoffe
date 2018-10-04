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
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.makecoffee.MakeCoffeeFragment;
import com.roger.makecoffee.objects.MakeCoffeeData;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public class MakeCoffeeAdapter extends RecyclerView.Adapter {
    private MakeCoffeeFragment mMakeCoffeeFragment;

    public MakeCoffeeAdapter(MakeCoffeeFragment fragment) {
        mMakeCoffeeFragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_make_coffee, parent, false);
        return new MakeCoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        bindMakeCoffeeViewHolder((MakeCoffeeViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return MakeCoffeeData.getInstance().getMakeCoffeeDataSize();
    }

    private void bindMakeCoffeeViewHolder(MakeCoffeeViewHolder holder, int position) {
        final MakeCoffeeTeaching teaching
                = MakeCoffeeData.getInstance().getMakeCoffeeTeachingsArrayList().get(position);
        holder.mImageView.setImageResource(teaching.getCoffeeDrawableId());
        holder.mTextView.setText(teaching.getCoffeeName());

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakeCoffeeActivity)mMakeCoffeeFragment.getActivity()).transToMakeCoffeeDetail(teaching);
            }
        });
    }

    class MakeCoffeeViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        ImageView mImageView;
        TextView mTextView;

        public MakeCoffeeViewHolder(View itemView) {
            super(itemView);

            mConstraintLayout = itemView.findViewById(R.id.constraintLayout_make_coffee);
            mImageView = itemView.findViewById(R.id.imageView_item_make_coffee);
            mTextView = itemView.findViewById(R.id.textView_item_make_coffee);
        }
    }
}
