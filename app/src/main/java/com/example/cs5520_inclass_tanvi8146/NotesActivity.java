package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity implements AddEditNoteFragment.IAddButtonActions {

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

        mNotes.add(new Note("tanvi", "This is note 1 " +
                "\n" +
                "\n" +
                "Food —\n" +
                "\n" +
                "Lunch :\n" +
                "1. Puri bhaji shrikhand\n" +
                "2. Chhole Bhature\n" +
                "3. Puranpoli\n" +
                "4. Palak paneer, Naan/Kulcha/Lachha\n" +
                "\n" +
                "Dinner :\n" +
                "1. Pav bhaji\n" +
                "2. Varan bhat\n" +
                "3. Dosa chutney\n" +
                "4. Misal\n" +
                "5. Noodles/Schezwan rice, Manchurian\n" +
                "6. Batata Vada\n" +
                "\n" +
                "Snacks :\n" +
                "1. Samosa\n" +
                "2. Kanda, Batata bhaji\n" +
                "\n" +
                "Places —\n" +
                "1. Lake Raleigh\n" +
                "2. Walnut Creek Trail\n" +
                "3. Patel Bros\n" +
                "4. Deja Vu Thrift Store\n" +
                "5. NCSU area\n ", "1", 0));

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

        getSupportFragmentManager().beginTransaction()
                .add(R.id.addEditNotePanel, AddEditNoteFragment.newInstance(), "add fragment")
                .commit();

    }

    @Override
    public void addButtonClicked(Note note) {
        mNotes.add(note);
        mAdapter.notifyDataSetChanged();
    }

}