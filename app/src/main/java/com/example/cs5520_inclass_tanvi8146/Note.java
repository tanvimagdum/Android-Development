package com.example.cs5520_inclass_tanvi8146;

public class Note {
    private String userId;
    private String text;
    private String _id;
    private int __v;

    public Note(String userId, String text, String _id, int __v) {
        this.userId = userId;
        this.text = text;
        this._id = _id;
        this.__v = __v;
    }

    public Note(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
