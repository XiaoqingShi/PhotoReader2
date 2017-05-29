package com.example.adminer.gettable.dao;

import java.io.Serializable;

/**
 * Created by adminer on 2017/5/29.
 */

public class User implements Serializable{



    private int id;
    private String username;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    private String password;
    private String sex;
    private String email;
    private String phonenumber;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public int getScore() {
        return score;
    }

    public int getMission() {
        return mission;
    }

    private int score;
    private int mission;
}
