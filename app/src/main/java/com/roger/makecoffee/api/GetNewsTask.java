package com.roger.makecoffee.api;

import android.os.AsyncTask;
import android.util.Log;

import com.roger.makecoffee.api.callbacks.GetNewsCallBack;
import com.roger.makecoffee.objects.NewsData;
import com.roger.makecoffee.objects.define.News;
import com.roger.makecoffee.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetNewsTask extends AsyncTask<Void, Void, ArrayList<News>> {
    private static final String  TAG = "GetNewsTask";
    private GetNewsCallBack mCallBack;

    public GetNewsTask(GetNewsCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected ArrayList<News> doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        // Request build
        Request request = new Request.Builder()
                .url(Constants.NEWS_URL)
                .get()
                .build();

        Log.d(TAG, "GET " + request.url());
        Log.d(TAG, request.headers().toString());

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert response != null;
        return doResponse(response);
    }

    @Override
    protected void onPostExecute(ArrayList<News> newsArrayList) {
        if (newsArrayList != null) {
            NewsData.getInstance().addNews(newsArrayList);
            mCallBack.onCompleted();
        } else {
            mCallBack.onError();
        }
    }

    private ArrayList<News> doResponse(Response response) {
        if (response.isSuccessful()) {
            try {
                try {
                    return changeJsonToNews(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ArrayList<News> changeJsonToNews(String response) throws JSONException {
        JSONObject obj = new JSONObject(response);
        if (obj.has(Constants.STATUS) && obj.getString(Constants.STATUS).equals(Constants.OK)) {
            JSONArray articles = obj.getJSONArray(Constants.ARTICLES);
            if (articles.length() != 0) {
                ArrayList<News> result = new ArrayList<>();
                for (int i = 0; i < articles.length(); i++) {
                    JSONObject article = articles.getJSONObject(i);
                    News news = new News();
                    news.setSource(article.getJSONObject(Constants.SOURCE).getString(Constants.NAME));
                    news.setAuthor(article.getString(Constants.AUTHOR));
                    news.setTitle(article.getString(Constants.TITLE));
                    news.setUrl(article.getString(Constants.URL));
                    news.setUrlToImage(article.getString(Constants.URL_TO_IMAGE));
                    news.setPublishedAt(article.getString(Constants.PUBLISH_AT));
                    news.setContent(article.getString(Constants.CONTENT));

                    result.add(news);
                }
                return result;
            }
        }
        return null;
    }
}
