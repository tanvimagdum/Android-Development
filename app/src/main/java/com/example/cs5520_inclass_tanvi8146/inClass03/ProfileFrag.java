package com.example.cs5520_inclass_tanvi8146.inClass03;

import java.io.Serializable;

public class ProfileFrag implements Serializable {

    private String name;
    private String email;
    private String os;
    private String mood;
    private int imgAvatar;
    private int imgMood;

    public ProfileFrag(String name, String email, String os, String mood, int imgAvatar, int imgMood) {
        this.name = name;
        this.email = email;
        this.os = os;
        this.mood = mood;
        this.imgAvatar = imgAvatar;
        this.imgMood = imgMood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(int imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public int getImgMood() {
        return imgMood;
    }

    public void setImgMood(int imgMood) {
        this.imgMood = imgMood;
    }
}
