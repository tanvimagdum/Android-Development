package com.example.cs5520_inclass_tanvi8146.inClass08;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class Message {

    private String textMessage;
    private String senderId;
    private String receiverId;
    private String timestamp;

    public Message() {
    }

    public Message(String textMessage, String senderId, String receiverId, String timestamp) {
        this.textMessage = textMessage;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "textMessage='" + textMessage + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
