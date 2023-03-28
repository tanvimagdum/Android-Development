package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ChatListAdapter extends FirestoreRecyclerAdapter<User, ChatListAdapter.ChatListViewHolder> {

    public ChatListAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatListViewHolder holder, int position, @NonNull User model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list, parent, false);
        return new ChatListViewHolder(view);
    }

    static class ChatListViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtChatUsername);
        }

        public void bind(User user) {
            name.setText(user.getFirstName());
        }
    }
}