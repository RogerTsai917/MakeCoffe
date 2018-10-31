package com.roger.makecoffee.news;

import android.util.Log;

import com.roger.makecoffee.api.GetNewsTask;
import com.roger.makecoffee.api.callbacks.GetNewsCallBack;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;

public class NewsPresenter implements NewsContract.Presenter {
    private static final String TAG = "NewsPresenter";
    private NewsContract.View mNewsView;
    private boolean isLoading = false;

    NewsPresenter(NewsContract.View view) {
        mNewsView = view;
        mNewsView.setPresenter(this);
    }

    @Override
    public void loadNews() {
        if (!isLoading()) {
            setLoading(true);
            new GetNewsTask(new GetNewsCallBack() {
                @Override
                public void onCompleted() {
                    setLoading(false);
                    Log.d(TAG, "Load News onCompleted");
                    mNewsView.showNews();
                }

                @Override
                public void onError() {
                    setLoading(false);
                    Log.d(TAG, "Load News onError");
                }
            }).execute();
        }
    }

    @Override
    public void openWebView(String url) {
        ((MakeCoffeeActivity)mNewsView.getMakeCoffeeActivity()).openWebView(url);
    }

    @Override
    public void start() {
        loadNews();
    }

    private boolean isLoading() {
        return isLoading;
    }

    private void setLoading(boolean state) {
        isLoading = state;
    }
}
