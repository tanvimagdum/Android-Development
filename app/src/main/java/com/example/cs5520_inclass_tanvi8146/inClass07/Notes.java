package com.example.cs5520_inclass_tanvi8146.inClass07;

import java.util.List;

/*
 * Tanvi Prashant Magdum
 * Vivek Dantu
 * Assignment 07
 */

public class Notes {
    private List<Note> notes;

    public Notes(){

    }

    public Notes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void addNotes(Note note){
        this.getNotes().add(note);
    }

}
