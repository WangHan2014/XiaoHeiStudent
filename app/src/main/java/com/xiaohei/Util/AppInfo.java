package com.xiaohei.Util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfo {
    public static final String UPDATE_LOG = "重构了App代码\n新增了一个图标用于快速启动";
    public static final String LIVE_TIME = "2022-2-24";
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