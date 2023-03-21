package com.example.cs5520_inclass_tanvi8146.inClass07;

/*
 * Tanvi Prashant Magdum
 * Vivek Dantu
 * Assignment 07
 */

public class User {
    private String _id;
    private String name;
    private String email;
    private int __v;

    public User(String _id, String name, String email, int __v) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.__v = __v;
    }

    public User(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
