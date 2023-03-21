package com.example.cs5520_inclass_tanvi8146;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;
    private static Context ctx;

    public NotesAdapter(List<Note> notes, Context ctx) {
        this.mNotes = notes;
        this.ctx = ctx;
    }

    private static String authToken = "";

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

        SharedPreferences sh = this.ctx.getSharedPreferences("sharedpref", MODE_PRIVATE);
        authToken = sh.getString("authToken","");
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
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder().add("id", mNotes.get(holder.getAdapterPosition()).get_id()).build();
                Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/delete").post(formBody).addHeader("x-access-token", authToken).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        holder.itemView.post(new Runnable() {
                            @Override
                            public void run() {
                                CharSequence text = "Cannot delete Note. Please try again!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(ctx, text, duration);
                                toast.show();
                                Toast.makeText(ctx, text, duration);
                                return;
                            }
                        });


                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if(response.isSuccessful()) {
                            try {
                                JSONObject jsonRes = new JSONObject(response.body().string());
                                if(jsonRes.getBoolean("delete")){
                                    holder.itemView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mNotes.remove(holder.getAdapterPosition());
                                            notifyDataSetChanged();
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                holder.itemView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        CharSequence text = "Cannot delete Note. Please try again!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(ctx, text, duration);
                                        toast.show();
                                        Toast.makeText(ctx, text, duration);
                                        return;
                                    }
                                });

                            }
                        }else{

                            holder.itemView.post(new Runnable() {
                                @Override
                                public void run() {
                                    CharSequence text = "Cannot delete Note. Please try again!";
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(ctx, text, duration);
                                    toast.show();
                                    Toast.makeText(ctx, text, duration);
                                    return;
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
