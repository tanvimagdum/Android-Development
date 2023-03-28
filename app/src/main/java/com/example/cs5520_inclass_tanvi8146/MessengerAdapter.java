package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.ViewHolder> {

    private List<Message> mMessages;
    private String currentUserId;
    private Context ctx;

    public MessengerAdapter(List<Message> mMessages, String currentUserId, Context ctx) {
        this.mMessages = mMessages;
        this.currentUserId = currentUserId;
        this.ctx = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView messageText;
        private ImageButton imgbtnSendMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.txtMessengerChat);
            imgbtnSendMessage = itemView.findViewById(R.id.imgbtnSendMessage);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messenger_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = mMessages.get(position);


    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}
