package com.roger.makecoffee.liked;

import android.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.roger.makecoffee.R;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;


public class LikedFragment extends Fragment {
    private int firstValue = 3;

    public LikedFragment() {

    }

    public static LikedFragment newInstance() {
        return new LikedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_write_article_detail_coffee_flavor, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        BarChartModel barChartModel = new BarChartModel();
//        barChartModel.setBarValue(firstValue);
//        barChartModel.setBarColor(Color.parseColor("#f4a149"));
//        barChartModel.setBarTag(null); //You can set your own tag to bar model
//        barChartModel.setBarText("" + firstValue);
//
//        barChart.addBar(barChartModel);
//        barChart.getBar(0).getBarValue();

    }
}
