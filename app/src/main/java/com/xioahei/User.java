package com.xioahei;

public class User {
    public static User user;
    public String No, Name, Class, College, School;

    private User() {

    }

    public static User getInstance() {
        if (user == null)
            user = new User();
        return user;
    }
}