package com.example.roomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * TODO
 * shige chen on 2020/2/26
 * shigechen@globalsources.com
 */
@Entity
public class UserInfoEntity {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo
    private String userName;
    @ColumnInfo
    private String userAge;

    @ColumnInfo
    private String userKg;
    @ColumnInfo
    private String userKg2;

    @ColumnInfo
    private String userHeight;

    @ColumnInfo
    private String phoneNum;

    @ColumnInfo
    private String phoneNum2;

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserKg2() {
        return userKg2;
    }

    public void setUserKg2(String userKg2) {
        this.userKg2 = userKg2;
    }

    public String getUserKg() {
        return userKg;
    }

    public void setUserKg(String userKg) {
        this.userKg = userKg;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
