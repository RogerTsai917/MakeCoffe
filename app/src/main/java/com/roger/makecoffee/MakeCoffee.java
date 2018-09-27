package com.roger.makecoffee;

import android.app.Application;
import android.content.Context;

public class MakeCoffee extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
