package com.yonimor.sporteam.sporteam.com.data;

import java.io.Serializable;

/**
 * Created by TheYoni on 20/10/2017.
 */

public class User implements Serializable{
    private String email,password, gender, userName, phone;
    private int age;

    public User(String userName, String password, String email, String gender,  String phone, int age)
    {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.userName = userName;
        this.phone = phone;
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
