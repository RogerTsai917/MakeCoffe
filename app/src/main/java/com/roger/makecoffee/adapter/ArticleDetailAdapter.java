package com.roger.makecoffee.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.roger.makecoffee.R;
import com.roger.makecoffee.articledetail.ArticleDetailFragment;
import com.roger.makecoffee.objects.LikedArticlesData;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;
import com.roger.makecoffee.utils.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ArticleDetailAdapter extends RecyclerView.Adapter {
    private ArticleDetailFragment mFragment;
    private NewArticle mArticle;


    public ArticleDetailAdapter(ArticleDetailFragment fragment, NewArticle article) {
        mFragment = fragment;
        mArticle = article;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_ARTICLE_TITLE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article_detail_title, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == Constants.VIEW_TYPE_ARTICLE_COFFEE_DETAIL) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article_detail_coffee_detail, parent, false);
            return new CoffeeDetailViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_article_detail_coffee_flavor, parent, false);
            return new CoffeeFlavorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            bindTitleViewHolder((TitleViewHolder) holder);
        } else if (holder instanceof CoffeeDetailViewHolder) {
            bindCoffeeDetailViewHolder((CoffeeDetailViewHolder) holder);
        } else if (holder instanceof CoffeeFlavorViewHolder) {
            bindCoffeeFlavorViewHolder((CoffeeFlavorViewHolder) holder);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Constants.VIEW_TYPE_ARTICLE_TITLE;
        } else if (position == 1) {
            return Constants.VIEW_TYPE_ARTICLE_COFFEE_DETAIL;
        } else {
            return Constants.VIEW_TYPE_ARTICLE_COFFEE_FLAVOR;
        }
    }

    private void bindTitleViewHolder(TitleViewHolder holder) {
        Glide.with(mFragment.getActivity())
                .load(mArticle.getImageUrl())
                .into(holder.mArticleImageView);
        Glide.with(mFragment.getActivity())
                .load(mArticle.getAuthor().getImage())
                .into(holder.mAuthorImageCircleImageView);

        holder.mTitleTextView.setText(mArticle.getTitle());
        holder.mAuthorNameTextView.setText(mArticle.getAuthor().getName());
        holder.mContentTextView.setText(mArticle.getContent());

        if (mArticle.getAuthor().getUid().equals(UserManager.getInstance().getUserUid())) {
            holder.mLikedImageView.setVisibility(View.GONE);
        } else if (LikedArticlesData.getInstance().isLikedArticle(mArticle.getArticleUid())) {
            holder.mLikedImageView.setImageResource(R.drawable.btn_like_selected);

        } else {
            holder.mLikedImageView.setImageResource(R.drawable.btn_like_normal);
        }

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = simpleDateFormat.format(mArticle.getCreatedTime());
        holder.mPostTimeTextView.setText(stringDate);
    }

    private void bindCoffeeDetailViewHolder(CoffeeDetailViewHolder holder) {
        setTextView(holder.mCoffeeBean, holder.mCoffeeBeanPrint, mArticle.getCoffeeBean());
        setTextView(holder.mCoffeeBeanRoastLevel, holder.mCoffeeBeanRoastLevelPrint, mArticle.getCoffeeBeanRoastLevel());
        setTextView(holder.mCoffeeBeanGrind, holder.mCoffeeBeanGrindPrint, mArticle.getCoffeeBeanGrind());
        setTextView(holder.mCoffeeBeanWeight, holder.mCoffeeBeanWeightPrint, mArticle.getCoffeeBeanWeight());
        setTextView(holder.mWaterTemp, holder.mWaterTempPrint, mArticle.getWaterTemp());
        setTextView(holder.mWaterVolume, holder.mWaterVolumePrint, mArticle.getWaterVolume());
        setTextView(holder.mWaterCoffeeTool, holder.mWaterCoffeeToolPrint, mArticle.getCoffeeTool());
        setTextView(holder.mTime, holder.mTimePrint, mArticle.getTime());
        setTextView(holder.mCoffeeVolume, holder.mCoffeeVolumePrint, mArticle.getWaterVolume());
        setTextView(holder.mCoffeeAdditive, holder.mCoffeeAdditivePrint, mArticle.getAdditive());
    }

    private void bindCoffeeFlavorViewHolder(CoffeeFlavorViewHolder holder) {
        setTextView(holder.mSupplement, holder.mSupplementPrint, mArticle.getSupplement());
    }

    private void setTextView(TextView textView, TextView textViewPrint, String input) {
        if (input == null || input.equals("")) {
            textView.setVisibility(View.GONE);
            textViewPrint.setVisibility(View.GONE);
        } else {
            textViewPrint.setText(input);
        }
    }

    private BarData getNewBarData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, mArticle.getFlavorAroma()));
        entries.add(new BarEntry(1f, mArticle.getFlavorSweet()));
        entries.add(new BarEntry(2f, mArticle.getFlavorBitter()));
        entries.add(new BarEntry(3f, mArticle.getFlavorAcidity()));
        entries.add(new BarEntry(4f, mArticle.getFlavorBody()));

        BarDataSet barDataSet = new BarDataSet(entries, "Bar Data Set");
        setBarDataSetColor(barDataSet);
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(Color.argb(40, 150, 150, 150));

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);

        return data;
    }

    private void  setBarDataSetColor(BarDataSet barDataSet) {
        barDataSet.setColors(
                mFragment.getContext().getColor(R.color.brown_f4),
                mFragment.getContext().getColor(R.color.brown_f4),
                mFragment.getContext().getColor(R.color.brown_f4),
                mFragment.getContext().getColor(R.color.brown_f4),
                mFragment.getContext().getColor(R.color.brown_f4)
        );
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        ImageView mArticleImageView;
        ImageView mLikedImageView;
        CircleImageView mAuthorImageCircleImageView;
        TextView mTitleTextView;
        TextView mAuthorNameTextView;
        TextView mPostTimeTextView;
        TextView mContentTextView;

        TitleViewHolder(View itemView) {
            super(itemView);
            mArticleImageView = itemView.findViewById(R.id.imageView_article_detail_image);
            mLikedImageView = itemView.findViewById(R.id.imageView_article_detail_like);
            mAuthorImageCircleImageView = itemView.findViewById(R.id.circleImageView_article_detail_author_image);
            mTitleTextView = itemView.findViewById(R.id.textView_article_detail_title);
            mAuthorNameTextView = itemView.findViewById(R.id.textView_article_detail_author_name);
            mPostTimeTextView = itemView.findViewById(R.id.textView_article_detail_posted_time);
            mContentTextView = itemView.findViewById(R.id.textView_article_detail_content);
        }
    }

    private class CoffeeDetailViewHolder extends RecyclerView.ViewHolder {
        TextView mCoffeeBean;
        TextView mCoffeeBeanPrint;
        TextView mCoffeeBeanRoastLevel;
        TextView mCoffeeBeanRoastLevelPrint;
        TextView mCoffeeBeanGrind;
        TextView mCoffeeBeanGrindPrint;
        TextView mCoffeeBeanWeight;
        TextView mCoffeeBeanWeightPrint;
        TextView mWaterTemp;
        TextView mWaterTempPrint;
        TextView mWaterVolume;
        TextView mWaterVolumePrint;
        TextView mWaterCoffeeTool;
        TextView mWaterCoffeeToolPrint;
        TextView mTime;
        TextView mTimePrint;
        TextView mCoffeeVolume;
        TextView mCoffeeVolumePrint;
        TextView mCoffeeAdditive;
        TextView mCoffeeAdditivePrint;

        CoffeeDetailViewHolder(View itemView) {
            super(itemView);
            mCoffeeBean = itemView.findViewById(R.id.textView_article_detail_coffee_bean);
            mCoffeeBeanPrint = itemView.findViewById(R.id.textView_article_detail_coffee_bean_print);
            mCoffeeBeanRoastLevel = itemView.findViewById(R.id.textView_article_detail_roast_level);
            mCoffeeBeanRoastLevelPrint = itemView.findViewById(R.id.textView_article_detail_roast_level_print);
            mCoffeeBeanGrind = itemView.findViewById(R.id.textView_article_detail_coffee_bean_grind);
            mCoffeeBeanGrindPrint = itemView.findViewById(R.id.textView_article_detail_coffee_bean_grind_print);
            mCoffeeBeanWeight = itemView.findViewById(R.id.textView_article_detail_coffee_bean_weight);
            mCoffeeBeanWeightPrint = itemView.findViewById(R.id.textView_article_detail_coffee_bean_weight_print);
            mWaterTemp = itemView.findViewById(R.id.textView_article_detail_water_temp);
            mWaterTempPrint = itemView.findViewById(R.id.textView_article_detail_water_temp_print);
            mWaterVolume = itemView.findViewById(R.id.textView_article_detail_water_volume);
            mWaterVolumePrint = itemView.findViewById(R.id.textView_article_detail_water_volume_print);
            mWaterCoffeeTool = itemView.findViewById(R.id.textView_article_detail_coffee_tool);
            mWaterCoffeeToolPrint = itemView.findViewById(R.id.textView_article_detail_coffee_tool_print);
            mTime = itemView.findViewById(R.id.textView_article_detail_coffee_time);
            mTimePrint = itemView.findViewById(R.id.textView_article_detail_coffee_time_print);
            mCoffeeVolume = itemView.findViewById(R.id.textView_article_detail_coffee_volume);
            mCoffeeVolumePrint = itemView.findViewById(R.id.textView_article_detail_coffee_volume_print);
            mCoffeeAdditive = itemView.findViewById(R.id.textView_article_detail_coffee_additive);
            mCoffeeAdditivePrint = itemView.findViewById(R.id.textView_article_detail_coffee_additive_print);
        }
    }

    private class CoffeeFlavorViewHolder extends RecyclerView.ViewHolder {
        HorizontalBarChart mBarChart;
        TextView mSupplement;
        TextView mSupplementPrint;

        CoffeeFlavorViewHolder(View itemView) {
            super(itemView);
            mBarChart = itemView.findViewById(R.id.HorizontalBarChart_article_detail_coffee_flavor);
            mSupplement = itemView.findViewById(R.id.textView_article_detail_supplement);
            mSupplementPrint = itemView.findViewById(R.id.textView_article_detail_supplement_print);

            mBarChart.setTouchEnabled(false);

            mBarChart.setDrawBarShadow(false);
            mBarChart.getDescription().setEnabled(false);

            mBarChart.getLegend().setEnabled(false);
            mBarChart.setPinchZoom(false);
            mBarChart.setDrawValueAboveBar(false);

            mBarChart.getXAxis().setDrawGridLines(false);
            mBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            mBarChart.getXAxis().setEnabled(true);
            mBarChart.getXAxis().setDrawAxisLine(false);

            mBarChart.getAxisLeft().setAxisMaximum(5f);
            mBarChart.getAxisLeft().setAxisMinimum(0f);
            mBarChart.getAxisLeft().setEnabled(false);

            mBarChart.getXAxis().setLabelCount(5);

            String[] values = new String[5];
            values[0] = mFragment.getResources().getString(R.string.flavor_aroma);
            values[1] = mFragment.getResources().getString(R.string.flavor_sweet);
            values[2] = mFragment.getResources().getString(R.string.flavor_bitter);
            values[3] = mFragment.getResources().getString(R.string.flavor_acidity);
            values[4] = mFragment.getResources().getString(R.string.flavor_body);

            IAxisValueFormatter valueFormatter = new IndexAxisValueFormatter(values);
            mBarChart.getXAxis().setValueFormatter(valueFormatter);
            mBarChart.getXAxis().setTextSize(16f);

            mBarChart.getAxisRight().setDrawAxisLine(true);
            mBarChart.getAxisRight().setDrawGridLines(false);
            mBarChart.getAxisRight().setEnabled(false);

            mBarChart.animateY(2000);

            mBarChart.setDrawBarShadow(true);

            mBarChart.setData(getNewBarData());

            mBarChart.invalidate();
        }
    }


}
