package com.example.cs5520_inclass_tanvi8146;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HeavyWork implements Runnable {

    public final static String KEY_PROGRESS = "KEY_PROGRESS";
    public final static int STATUS_PROGRESS = 0x001;
    public final static String KEY_MIN = "KEY_MIN";
    public final static String KEY_MAX = "KEY_MAX";
    public final static String KEY_AVG = "KEY_AVG";
    public final static int STATUS_VALUES = 0x002;

    private int complexity;
    private static Handler messageQueue;

    public HeavyWork(int complexity, Handler messageQueue) {
        this.complexity = complexity;
        this.messageQueue = messageQueue;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }

    static final int COUNT = 9000000;
    static ArrayList<Double> getArrayNumbers(int n){
        ArrayList<Double> returnArray = new ArrayList<>();

        for (int i = 0; i < n; i++){
            returnArray.add(getNumber());

            Message progressMessage = new Message();
            Bundle bundleProgress = new Bundle();
            bundleProgress.putInt(KEY_PROGRESS, i);
            progressMessage.what = STATUS_PROGRESS;
            progressMessage.setData(bundleProgress);
            messageQueue.sendMessage(progressMessage);

        }

        return returnArray;
    }

    static double getNumber(){
        double num = 0;
        Random ran = new Random();
        for(int i = 0; i < COUNT; i++){

            num = num + (Math.random() * ran.nextDouble() * 100 + ran.nextInt(50)) * 1000;

        }
        return num / ((double) COUNT);
    }

    private double calculateMinimum(ArrayList<Double> numberArray) {
        double min = numberArray.get(0);
        for (double x : numberArray) {
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    private double calculateMaximum(ArrayList<Double> numberArray) {
        double max = 0;
        for (double x : numberArray) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    private double calculateAverage(ArrayList<Double> numberArray) {
        double avg;
        double sum = 0;
        for (double x : numberArray) {
            sum = sum + x;
        }
        avg = sum / numberArray.size();
        return avg;
    }

    @Override
    public void run() {

        int complexity = getComplexity();
        ArrayList<Double> list = getArrayNumbers(complexity);
        double min = calculateMinimum(list);
        double max = calculateMaximum(list);
        double avg = calculateAverage(list);

        Message valuesMessage = new Message();
        Bundle bundleValue = new Bundle();
        bundleValue.putDouble(KEY_MIN, min);
        bundleValue.putDouble(KEY_MAX, max);
        bundleValue.putDouble(KEY_AVG, avg);
        valuesMessage.what = STATUS_VALUES;
        valuesMessage.setData(bundleValue);
        messageQueue.sendMessage(valuesMessage);

    }

    // public static void main(String[] args) {
    //     ArrayList<Double> arrayList = new ArrayList<>();
    //     arrayList = getArrayNumbers(200);
    //     for(double num: arrayList){
    //         System.out.println(num);
    //     }
    // }
}