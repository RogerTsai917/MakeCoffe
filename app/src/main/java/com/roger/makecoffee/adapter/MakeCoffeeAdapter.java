package com.roger.makecoffee.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roger.makecoffee.R;

public class MakeCoffeeAdapter extends RecyclerView.Adapter {


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
        //temp
        return 10;
    }

    private void bindMakeCoffeeViewHolder(MakeCoffeeViewHolder holder, int position) {
        int count = position % 4;

        switch (count) {
            case 0:
                holder.mImageView.setImageResource(R.drawable.coffee_americano);
                break;
            case 1:
                holder.mImageView.setImageResource(R.drawable.coffee_espresso);
                break;
            case 2:
                holder.mImageView.setImageResource(R.drawable.coffee_latte);
                break;
            case 3:
                holder.mImageView.setImageResource(R.drawable.coffee_cappuccino);
                break;
            default:
        }
    }

    class MakeCoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public MakeCoffeeViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView_item_make_coffee);
            mTextView = itemView.findViewById(R.id.textView_item_make_coffee);
        }
    }
}
