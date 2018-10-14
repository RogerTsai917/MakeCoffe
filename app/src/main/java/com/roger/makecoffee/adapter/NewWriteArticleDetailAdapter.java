package com.roger.makecoffee.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.utils.Constants;
import com.roger.makecoffee.writearticle.WriteArticleFragment;

import java.util.ArrayList;

import me.ithebk.barchart.BarChartModel;

public class NewWriteArticleDetailAdapter extends RecyclerView.Adapter{
    private WriteArticleFragment mFragment;
    private NewArticle mNewArticle;
    private HorizontalBarChart mBarChart;

    public NewWriteArticleDetailAdapter(WriteArticleFragment fragment) {
        mFragment = fragment;
        mNewArticle = new NewArticle();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Constants.VIEW_TYPE_WRITE_ARTICLE_TITLE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_title, parent, false);
            return new TitleViewHolder(view);
        } else if (viewType == Constants.VIEW_TYPE_WRITE_ARTICLE_COFFEE_DETAIL) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_coffee_detail, parent, false);
            return new CoffeeDetailViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_write_article_detail_coffee_flavor, parent, false);
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
            return Constants.VIEW_TYPE_WRITE_ARTICLE_TITLE;
        } else if (position == 1) {
            return Constants.VIEW_TYPE_WRITE_ARTICLE_COFFEE_DETAIL;
        } else {
            return Constants.VIEW_TYPE_WRITE_ARTICLE_COFFEE_FLAVOR;
        }
    }

    private void bindTitleViewHolder(final TitleViewHolder holder) {
        holder.mTitleEditText.setText(mNewArticle.getTitle());
        holder.mContentEditText.setText(mNewArticle.getContent());

        holder.mUploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.getImageFromAlbum();

            }
        });

        if (!mNewArticle.getImageUrl().equals("")) {
            Uri uri = Uri.parse(mNewArticle.getImageUrl());
            Glide.with(mFragment)
                    .load(uri)
                    .into(holder.mPreviewImageView);
            holder.mPreviewImageView.setVisibility(View.VISIBLE);
        }

        holder.mTitleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setTitle(holder.mTitleEditText.getText().toString());
            }
        });
        holder.mContentEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setContent(holder.mContentEditText.getText().toString());
            }
        });

    }

    private void bindCoffeeDetailViewHolder(final CoffeeDetailViewHolder holder) {
        holder.mCoffeeBeanEditText.setText(mNewArticle.getCoffeeBean());
        holder.mCoffeeBeanRoastLevelEditText.setText(mNewArticle.getCoffeeBeanRoastLevel());
        holder.mCoffeeBeanGrindEditText.setText(mNewArticle.getCoffeeBeanGrind());
        holder.mCoffeeBeanVolumeEditText.setText(mNewArticle.getCoffeeBeanVolume());
        holder.mWaterTempEditText.setText(mNewArticle.getWaterTemp());
        holder.mWaterVolumeEditText.setText(mNewArticle.getWaterVolume());
        holder.mCoffeeToolEditText.setText(mNewArticle.getCoffeeTool());
        holder.mTimeEditText.setText(mNewArticle.getTime());
        holder.mCoffeeVolumeEditText.setText(mNewArticle.getCoffeeVolume());

        holder.mCoffeeBeanEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeBean(holder.mCoffeeBeanEditText.getText().toString());
            }
        });
        holder.mCoffeeBeanRoastLevelEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeBeanRoastLevel(holder.mCoffeeBeanRoastLevelEditText.getText().toString());
            }
        });
        holder.mCoffeeBeanGrindEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeBeanGrind(holder.mCoffeeBeanGrindEditText.getText().toString());
            }
        });
        holder.mCoffeeBeanVolumeEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeBeanVolume(holder.mCoffeeBeanVolumeEditText.getText().toString());
            }
        });
        holder.mWaterTempEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setWaterTemp(holder.mWaterTempEditText.getText().toString());
            }
        });
        holder.mWaterVolumeEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setWaterVolume(holder.mWaterVolumeEditText.getText().toString());
            }
        });
        holder.mCoffeeToolEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeTool(holder.mCoffeeToolEditText.getText().toString());
            }
        });
        holder.mTimeEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setTime(holder.mTimeEditText.getText().toString());
            }
        });
        holder.mCoffeeVolumeEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setCoffeeVolume(holder.mCoffeeVolumeEditText.getText().toString());
            }
        });

    }

    private void bindCoffeeFlavorViewHolder(final CoffeeFlavorViewHolder holder) {
        holder.mSupplementEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewArticle.setSupplement(holder.mSupplementEditText.getText().toString());
            }
        });

        holder.mBarChartConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.showChangeCoffeeFlavorDialog();
            }
        });
    }

    private BarData getNewBarData() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, mNewArticle.getFlavorAroma()));
        entries.add(new BarEntry(1f, mNewArticle.getFlavorSweet()));
        entries.add(new BarEntry(2f, mNewArticle.getFlavorBitter()));
        entries.add(new BarEntry(3f, mNewArticle.getFlavorAcidity()));
        entries.add(new BarEntry(4f, mNewArticle.getFlavorBody()));

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

    public void refreshBarChartData(int[] newLevel) {
        mNewArticle.setFlavorBody(newLevel[0]);
        mNewArticle.setFlavorAcidity(newLevel[1]);
        mNewArticle.setFlavorBitter(newLevel[2]);
        mNewArticle.setFlavorSweet(newLevel[3]);
        mNewArticle.setFlavorAroma(newLevel[4]);
    }

    public void refreshBarChart() {
        mBarChart.setData(getNewBarData());
        mBarChart.animateY(1000);
        mBarChart.invalidate();
    }

    public NewArticle getNewArticle() {
        return mNewArticle;
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        EditText mTitleEditText;
        EditText mContentEditText;
        ImageView mPreviewImageView;
        Button mUploadPhotoButton;

        public TitleViewHolder(View itemView) {
            super(itemView);
            mTitleEditText = itemView.findViewById(R.id.editText_write_article_title_title);
            mContentEditText = itemView.findViewById(R.id.editText_write_article_title_description);
            mPreviewImageView = itemView.findViewById(R.id.imageView_write_article_title);
            mUploadPhotoButton = itemView.findViewById(R.id.button_write_article_title);

            mPreviewImageView.setClipToOutline(true);
        }
    }

    private class CoffeeDetailViewHolder extends RecyclerView.ViewHolder {
        EditText mCoffeeBeanEditText;
        EditText mCoffeeBeanRoastLevelEditText;
        EditText mCoffeeBeanGrindEditText;
        EditText mCoffeeBeanVolumeEditText;
        EditText mWaterTempEditText;
        EditText mWaterVolumeEditText;
        EditText mCoffeeToolEditText;
        EditText mTimeEditText;
        EditText mCoffeeVolumeEditText;

        public CoffeeDetailViewHolder(View itemView) {
            super(itemView);
            mCoffeeBeanEditText = itemView.findViewById(R.id.editText_write_article_coffee_bean);
            mCoffeeBeanRoastLevelEditText = itemView.findViewById(R.id.editText_write_article_coffee_bean_roast_level);
            mCoffeeBeanGrindEditText = itemView.findViewById(R.id.editText_write_article_coffee_bean_grind);
            mCoffeeBeanVolumeEditText = itemView.findViewById(R.id.editText_write_article_coffee_bean_volume);
            mWaterTempEditText = itemView.findViewById(R.id.editText_write_article_coffee_water_temp);
            mWaterVolumeEditText = itemView.findViewById(R.id.editText_write_article_coffee_water_volume);
            mCoffeeToolEditText = itemView.findViewById(R.id.editText_write_article_coffee_tool);
            mTimeEditText = itemView.findViewById(R.id.editText_write_article_coffee_time);
            mCoffeeVolumeEditText = itemView.findViewById(R.id.editText_write_article_coffee_volume);
        }
    }

    private class CoffeeFlavorViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mBarChartConstraintLayout;
        EditText mSupplementEditText;

        public CoffeeFlavorViewHolder(View itemView) {
            super(itemView);

            mBarChartConstraintLayout = itemView.findViewById(R.id.constraintLayout_barChart_coffee_flavor);
            mSupplementEditText = itemView.findViewById(R.id.editText_write_article_supplement);
            mBarChart = itemView.findViewById(R.id.HorizontalBarChart_coffee_flavor);

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
