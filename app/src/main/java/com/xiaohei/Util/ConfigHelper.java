package com.xiaohei.Util;

//用于保存一些全局配置

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigHelper {

    public static boolean isFirstGoOut(Context context) {
        SharedPreferences data = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return data.getBoolean("isFirstGoOut", true);
    }

    public static boolean setFirstGoOut(boolean flag, Context context) {
        try {
            SharedPreferences data = context.getSharedPreferences("config", Context.MODE_PRIVATE);
            data.edit().putBoolean("isFirstGoOut", flag).apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}