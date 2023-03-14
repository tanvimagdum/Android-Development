package com.example.cs5520_inclass_tanvi8146.inClass05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.cs5520_inclass_tanvi8146.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/*
 * Tanvi Prashant Magdum
 * Assignment 05
 */

public class InClass05Activity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient().newBuilder()
                                            .readTimeout(10, TimeUnit.SECONDS)
                                            .connectTimeout(15, TimeUnit.SECONDS)
                                            .build();

    private TextView txtImageSearch;
    private TextView txtImageLoad;
    private Button btnGo;
    private ImageView imgDisplay;
    private ImageView imgPrev;
    private ImageView imgNext;
    private ProgressBar progressImageLoad;
    private int currentIndex;
    private String[] urlList;

    private boolean isInternetAvailable() {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(1000, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (ExecutionException | TimeoutException | InterruptedException e) {
        }

        return inetAddress!=null && !inetAddress.getHostAddress().equals("");
    }

    private void showLoading() {
        progressImageLoad.setVisibility(View.VISIBLE);
        txtImageLoad.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressImageLoad.setVisibility(View.INVISIBLE);
        txtImageLoad.setVisibility(View.INVISIBLE);
    }

    private void enableButtons() {
        imgPrev.setEnabled(true);
        imgNext.setEnabled(true);
    }

    private void disableButtons() {
        imgPrev.setEnabled(false);
        imgNext.setEnabled(false);
    }


    private void displayImage() {
        String imgUrl = urlList[currentIndex];
        showLoading();
        disableButtons();

        Glide.with(this)
                .load(imgUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        hideLoading();
                        Toast.makeText(InClass05Activity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        hideLoading();
                        return false;
                    }
                })
                .into(imgDisplay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle("Image Search");

        txtImageSearch = findViewById(R.id.txtImageSearch);
        txtImageLoad = findViewById(R.id.txtImageLoad);
        btnGo = findViewById(R.id.btnGo);
        imgDisplay = findViewById(R.id.imgDisplay);
        imgPrev = findViewById(R.id.imgPrev);
        imgNext = findViewById(R.id.imgNext);
        progressImageLoad = findViewById(R.id.progressImageLoad);

        hideLoading();
        disableButtons();

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String keyword = txtImageSearch.getText().toString();

                if (keyword.isEmpty()) {
                    Toast.makeText(InClass05Activity.this, "Please enter a search keyword.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isInternetAvailable()) {
                    txtImageLoad.setText("Loading...");
                    showLoading();

                    HttpUrl urlKeywords = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com/apis/images/retrieve")
                            .newBuilder()
                            .addQueryParameter("keyword", keyword)
                            .build();

                    Request request = new Request.Builder()
                            .url(urlKeywords)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideLoading();
                                    Toast.makeText(InClass05Activity.this, "Failed to retrieve images.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {
                                ResponseBody body = response.body();
                                String bodyString = body.string();
                                urlList = bodyString.split("\n");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideLoading();
                                        if (urlList.length == 0) {
                                            imgDisplay.setImageDrawable(null);
                                            Toast.makeText(InClass05Activity.this, "No images found", Toast.LENGTH_SHORT).show();
                                        } else {
                                            currentIndex = 0;
                                            displayImage();
                                            if (urlList.length > 1) {
                                                enableButtons();
                                            }
                                        }
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideLoading();
                                        Toast.makeText(InClass05Activity.this, "Please check for the correct keywords.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(InClass05Activity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex - 1 + urlList.length) % urlList.length;
                displayImage();
                enableButtons();
                txtImageLoad.setText("Loading previous...");
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % urlList.length;
                displayImage();
                enableButtons();
                txtImageLoad.setText("Loading next...");
            }
        });



    }
}