package com.example.cs5520_inclass_tanvi8146.inClass08_09;

import java.util.List;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class Messages {

    private List<Message> messages;

    public Messages() {
    }

    public Messages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessages(Message message) {
        this.getMessages().add(message);
    }

}
