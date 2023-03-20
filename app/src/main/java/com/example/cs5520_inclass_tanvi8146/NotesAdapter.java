package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;

    public NotesAdapter(List<Note> notes) {
        mNotes = notes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNotes = itemView.findViewById(R.id.txtNotes);
        }

        public TextView getTxtNotes() {
            return txtNotes;
        }

        public void setTxtNotes(TextView txtNotes) {
            this.txtNotes = txtNotes;
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {

        holder.getTxtNotes().setText(mNotes.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, NoteDetailActivity.class);
                intent.putExtra("note", mNotes.get(position).getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
