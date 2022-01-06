package com.xiaohei.User;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {

    public boolean saveUser(UserBean user, Context context) {
        try {
            SharedPreferences GoOut = context.getSharedPreferences("GoOut", Context.MODE_PRIVATE);
            SharedPreferences.Editor data = GoOut.edit();
            data.putString("Name", user.getName());
            data.putString("SNo", user.getSNo());
            data.putString("Classes", user.getClasses());
            data.putString("College", user.getCollege());
            data.putString("School", user.getSchool());
            data.apply();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public UserBean getUser(Context context) {
        SharedPreferences data = context.getSharedPreferences("GoOut", Context.MODE_PRIVATE);
        UserBean user = new UserBean();
        user.setName(data.getString("Name", "小牛马"));
        user.setSNo(data.getString("SNo", "6868686868"));
        user.setClasses(data.getString("Classes", "21牛马本0"));
        user.setCollege(data.getString("College", "牛马学院"));
        user.setSchool(data.getString("School", "鸡马牛羊学院"));
        return user;
    }
}