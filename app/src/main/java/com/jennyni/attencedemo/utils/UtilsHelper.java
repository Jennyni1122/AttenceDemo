package com.jennyni.attencedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * Created by Jenny on 2019/5/31.
 */

public class UtilsHelper {

    /**
     * 从SharedPreferences中读取登录用户名
     */
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");//读取登录时的用户名
        return userName;
    }
}
