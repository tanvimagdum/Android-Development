package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private List<Note> mNotes;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    private NotesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setTitle("Notes App");

        mNotes = new ArrayList<>();

        mNotes.add(new Note("tanvi", "This is note 1", "1", 0));
        mNotes.add(new Note("tanvi", "This is note 2", "2", 0));
        mNotes.add(new Note("tanvi", "This is note 3", "3", 0));
        mNotes.add(new Note("tanvi", "This is note 4", "4", 0));
        mNotes.add(new Note("tanvi", "This is note 5", "5", 0));
        mNotes.add(new Note("tanvi", "This is note 6", "6", 0));
        mNotes.add(new Note("tanvi", "This is note 7", "7", 0));
        mNotes.add(new Note("tanvi", "This is note 8", "8", 0));
        mNotes.add(new Note("tanvi", "This is note 9", "9", 0));

        mRecyclerView = findViewById(R.id.notesRecyclerView);
        mRecyclerViewLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mAdapter = new NotesAdapter(mNotes);
        mRecyclerView.setAdapter(mAdapter);



    }
}