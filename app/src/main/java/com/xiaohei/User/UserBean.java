package com.xiaohei.User;

public class UserBean {
    private String Name;
    private String SNo;
    private String Classes;
    private String College;
    private String School;

    public UserBean() {
        Name = "小牛马";
        SNo = "6868686868";
        Classes = "21牛马本0";
        College = "牛马学院";
        School = "鸡马牛羊学院";
    }

    public String getSNo() {
        return SNo;
    }

    public void setSNo(String SNo) {
        this.SNo = SNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String classes) {
        Classes = classes;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }
}