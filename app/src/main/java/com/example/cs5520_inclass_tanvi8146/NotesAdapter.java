package com.example.cs5520_inclass_tanvi8146;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        private TextView txtNotes;
        private Button btnNoteDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNotes = itemView.findViewById(R.id.txtNotes);
            btnNoteDelete = itemView.findViewById(R.id.btnNoteDelete);
        }

        public TextView getTxtNotes() {
            return txtNotes;
        }
        public Button getBtnNoteDelete() {
            return btnNoteDelete;
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
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

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

        holder.getBtnNoteDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotes.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
