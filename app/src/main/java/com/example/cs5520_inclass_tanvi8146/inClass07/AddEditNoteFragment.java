package com.example.cs5520_inclass_tanvi8146.inClass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Vivek Dantu
 * Assignment 07
 */

public class AddEditNoteFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Note note;
    private IAddButtonActions mListener;

    public AddEditNoteFragment() {

    }

    public static AddEditNoteFragment newInstance() {
        AddEditNoteFragment fragment = new AddEditNoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);
        EditText txtAddEditNote = view.findViewById(R.id.txtAddEditNote);
        Button btnAddEditNote = view.findViewById(R.id.btnAddEditNote);

        btnAddEditNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note();
                note.setText(txtAddEditNote.getText().toString());
                mListener.addButtonClicked(note);
                txtAddEditNote.setText("");
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IAddButtonActions) {
            mListener = (IAddButtonActions) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement IAddButtonActions interface.");
        }
    }

    public interface IAddButtonActions {
        void addButtonClicked(Note note);
    }

}