package com.jennyni.attencedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jennyni.attencedemo.app.MyApplication;

/**
 * Created by 969 on 2019/6/5.
 */

public class SPUtils {


    private static final String SP_NAME = "attence";
    public static final String PERCENT_KEY = "percent";

    public static void save(Object value, String key) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        }
        edit.commit();
    }

    public static int getInt(String key) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }
}
