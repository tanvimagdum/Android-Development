package com.example.cs5520_inclass_tanvi8146;

import java.io.Serializable;

public class Profile implements Serializable {

    protected String name;
    protected String email;
    protected String os;
    protected String mood;
    protected int imgAvatar;
    protected int imgMood;

    public Profile(String name, String email, String os, String mood, int imgAvatar, int imgMood) {
        this.name = name;
        this.email = email;
        this.os = os;
        this.mood = mood;
        this.imgAvatar = imgAvatar;
        this.imgMood = imgMood;
    }
}
