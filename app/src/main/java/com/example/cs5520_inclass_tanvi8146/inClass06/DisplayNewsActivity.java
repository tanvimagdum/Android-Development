package com.example.cs5520_inclass_tanvi8146.inClass06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cs5520_inclass_tanvi8146.R;

import java.io.Serializable;

/*
 * Tanvi Prashant Magdum
 * Assignment 06
 *
 */

public class DisplayNewsActivity extends AppCompatActivity implements Serializable {

    private TextView txtNewsTitle;
    private TextView txtNewsAuthor;
    private TextView txtNewsPublishedAt;
    private TextView txtNewsDescription;
    private ImageView imgNewsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        setTitle("Times Today");

        txtNewsTitle = findViewById(R.id.txtNewsTitle);
        txtNewsAuthor = findViewById(R.id.txtNewsAuthor);
        txtNewsPublishedAt = findViewById(R.id.txtNewsPublishedAt);
        txtNewsDescription = findViewById(R.id.txtNewsDescription);
        imgNewsImage = findViewById(R.id.imgNewsImage);

        if(getIntent() != null && getIntent().getExtras() != null) {
            Article article = (Article) getIntent().getSerializableExtra(InClass06Activity.News_Key);
            txtNewsTitle.setText(article.getTitle());
            txtNewsAuthor.setText(article.getAuthor());
            txtNewsPublishedAt.setText(article.getPublishedAt());
            txtNewsDescription.setText(article.getDescription());
            Glide.with(this).load(article.getUrlToImage()).into(imgNewsImage);
        }

    }
}