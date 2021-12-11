package com.xioahei.Util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfo {
    public static final String UPDATE_LOG = "修复了版本号问题\n修复了一个布局bug";
    public static final String LIVE_TIME = "2021-12-24";
    //public static String APP_VERSION;

    public static String APP_VERSION(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}