package com.example.cs5520_inclass_tanvi8146.inClass06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/*
 * Tanvi Prashant Magdum
 * Assignment 06
 */

public class InClass06Activity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private String apiUrl;

    private Spinner spinCategory;
    private Spinner spinCountry;
    private Button btnGetNews;
    private String selectedCategory;
    private String selectedCountry;
    private ListView listNews;
    private String API_key = "ffac31e2e2974a479dedc4ccac67f960";
    final static String News_Key = "showArticle";


    private String[] category = {"Select Category", "business", "entertainment", "general", "health", "science", "sports", "technology"};
    private String[] country = {"Select Country", "us", "in", "nz", "fr", "il"};


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class06);
        setTitle("Times Today");


        spinCategory = findViewById(R.id.spinCategory);
        spinCountry = findViewById(R.id.spinCountry);
        btnGetNews = findViewById(R.id.btnGetNews);
        listNews = findViewById(R.id.newsList);

        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, category);
        spinCategory.setAdapter(adapterCategory);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country);
        spinCountry.setAdapter(adapterCountry);

        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = category[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCategory = "Select Category";
            }
        });

        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCountry = country[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCountry = "Select Country";
            }
        });

        btnGetNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isInternetAvailable()) {

                    if (selectedCategory.equals("Select Category") && selectedCountry.equals("Select Country")) {
                        apiUrl = "https://newsapi.org/v2/top-headlines?apiKey=" + API_key;
                    }
                    else if (selectedCountry.equals("Select Country")) {
                        apiUrl = "https://newsapi.org/v2/top-headlines?apiKey=" + API_key
                                + "&category=" + selectedCategory;
                    }
                    else if (selectedCategory.equals("Select Category")){
                        apiUrl = "https://newsapi.org/v2/top-headlines?apiKey=" + API_key
                                + "&country=" + selectedCountry;
                    }
                    else {
                        apiUrl = "https://newsapi.org/v2/top-headlines?apiKey=" + API_key
                                + "&category=" + selectedCategory
                                + "&country=" + selectedCountry;
                    }

                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InClass06Activity.this, "Failed to retrieve information.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if (response.isSuccessful()) {

                                Gson gsonData = new Gson();
                                News apiResponse = gsonData.fromJson(response.body().string(), News.class);
                                List<Article> articles = apiResponse.getArticles();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        ArrayAdapter<Article> adapter = new ArrayAdapter<>(InClass06Activity.this, android.R.layout.simple_list_item_1, articles);
                                        listNews.setAdapter(adapter);

                                        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Article selectedArticle = articles.get(i);
                                                Intent intent = new Intent(InClass06Activity.this, DisplayNewsActivity.class);
                                                intent.putExtra(News_Key, selectedArticle);
                                                startActivity(intent);
                                            }
                                        });

                                    }
                                });


                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(InClass06Activity.this, "Please check for the correct parameters.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(InClass06Activity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}