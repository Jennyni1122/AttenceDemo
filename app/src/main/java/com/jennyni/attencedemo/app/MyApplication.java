package com.jennyni.attencedemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 969 on 2019/6/5.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
