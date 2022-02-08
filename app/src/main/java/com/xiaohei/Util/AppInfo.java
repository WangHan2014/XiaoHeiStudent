package com.xiaohei.Util;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppInfo {
    public static final String UPDATE_LOG = "如无更改将不再更新";
    public static final String LIVE_TIME = "2022-4-2";

    public final static String APP_VERSION(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}