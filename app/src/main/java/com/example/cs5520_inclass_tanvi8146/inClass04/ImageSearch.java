package com.example.cs5520_inclass_tanvi8146.inClass04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ImageSearch implements Runnable {

    private final OkHttpClient client = new OkHttpClient();

    public final static String KEY_PROGRESS = "KEY_PROGRESS";
    public final static int STATUS_PROGRESS = 0x001;
    public final static String KEY_MIN = "KEY_MIN";
    public final static String KEY_MAX = "KEY_MAX";
    public final static String KEY_AVG = "KEY_AVG";
    public final static int STATUS_VALUES = 0x002;

    private String keyword;
    private static Handler messageQueue;

    public ImageSearch(String keyword, Handler messageQueue) {
        this.keyword = keyword;
        this.messageQueue = messageQueue;
    }

    public void setComplexity(String keyword) {
        this.keyword = keyword;
    }

    public String getComplexity() {
        return keyword;
    }


    @Override
    public void run() {

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
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    Log.d("tag", "onResponse: " + responseBody.string());
                }
            }
        });


        Message valuesMessage = new Message();
        Bundle bundleValue = new Bundle();
        //bundleValue.putDouble(KEY_MIN, min);
        //bundleValue.putDouble(KEY_MAX, max);
        //bundleValue.putDouble(KEY_AVG, avg);
        valuesMessage.what = STATUS_VALUES;
        valuesMessage.setData(bundleValue);
        messageQueue.sendMessage(valuesMessage);

    }
}
