package com.xiaohei.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws Exception {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    //获取当前时间戳
    public static String getTimeStamp() {
        return String.valueOf(new Date().getTime());
    }
}
