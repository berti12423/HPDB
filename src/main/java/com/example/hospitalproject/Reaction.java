package com.example.hospitalproject;

import java.io.Serializable;

public class Reaction implements Serializable {
    private String code;
    private int rating;
    private String date,time;
    private String description;

    public Reaction(String code, int rating, String date, String description,String time) {
        this.code = code;
        this.time=time;
        this.rating = rating;
        this.date= date;
        this.description = description;
    }
    public Reaction(String code, String date, String description, String time)
    {
        this.code=code;
        this.time=time;
        this.description=description;
        this.date=date;
    }
    public Reaction() {}
    public Reaction(String username) {
        this.code = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time= time;
    }
    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date= date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
