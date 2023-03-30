package com.example.cs5520_inclass_tanvi8146.inClass08;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs5520_inclass_tanvi8146.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class ChatListAdapter extends FirestoreRecyclerAdapter<User, ChatListAdapter.ChatListViewHolder> {

    private OnUserClickListener mOnUserClickListener;

    public ChatListAdapter(@NonNull FirestoreRecyclerOptions<User> options, OnUserClickListener onUserClickListener) {
        super(options);
        mOnUserClickListener = onUserClickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatListViewHolder holder, int position, @NonNull User model) {
        holder.bind(model);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                String friendId = documentSnapshot.getId();

                mOnUserClickListener.onUserClick(friendId);
            }
        });
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

    public interface OnUserClickListener {
        void onUserClick(String friendId);
    }
}