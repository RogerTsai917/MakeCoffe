package com.roger.makecoffee.objects;

import com.roger.makecoffee.objects.define.News;

import java.util.ArrayList;

public class NewsData {
    private static NewsData mNewsData;
    private ArrayList<News> mNewsArrayList;

    private NewsData() {
        mNewsArrayList = new ArrayList<>();
    }

    public static synchronized NewsData getInstance() {
        if (mNewsData == null) {
            mNewsData = new NewsData();
        }
        return mNewsData;
    }

    public void addNews(ArrayList<News> newsArrayList) {
        mNewsArrayList.addAll(newsArrayList);
    }

    public ArrayList<News> getNewsArrayList() {
        return mNewsArrayList;
    }

    public int getNewsDataSize() {
        return mNewsArrayList.size();
    }

}
