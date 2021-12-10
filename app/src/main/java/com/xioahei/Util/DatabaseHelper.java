package com.xioahei.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "User";
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "xiaohei.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + TABLE_NAME + " (Id integer primary key, Name text, No text,Class text ,College text,School text)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("insert into User values(1, '小牛马', '6868686868','21牛马本0','牛马学院','鸡马牛羊学院')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
