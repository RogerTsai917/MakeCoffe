package com.roger.makecoffee;

import com.roger.makecoffee.objects.NewsData;
import com.roger.makecoffee.objects.define.News;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NewsDataUnitTest {
    private NewsData mNewsData;
    private int testValue = 3;

    @Before
    public void setUp() {
        mNewsData = NewsData.getInstance();
        ArrayList<News> arrayList = new ArrayList<>();
        for (int i = 0; i < testValue; i++) {
            arrayList.add(new News());
        }
        mNewsData.addNews(arrayList);
    }

    @Test
    public void articleIsLegal() {
        assertEquals(testValue, mNewsData.getNewsDataSize());
    }



}
