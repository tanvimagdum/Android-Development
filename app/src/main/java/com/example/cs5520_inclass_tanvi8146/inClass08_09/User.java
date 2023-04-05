package com.example.cs5520_inclass_tanvi8146.inClass08_09;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String photo;

    public User() {

    }

    public User(String firstName, String lastName, String email, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
