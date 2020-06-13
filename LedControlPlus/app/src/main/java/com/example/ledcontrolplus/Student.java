package com.example.ledcontrolplus;

import android.util.Log;

public class Student {
    private final String TAG = "com.example.ledcontrolplus.Student";
    public int age;
    public double height;
    public String name;

    public int getAge() {
        Log.d(TAG, "getAge = "+this.age);
        return this.age;
    }

    public double getHeight() {
        Log.d(TAG, "getHeight height = " + this.height);
        return this.height;
    }

    public String getName() {
        Log.d(TAG, "getName name = " + this.name);
        return this.name;
    }

    public void setAll(int age, double height, String name) {
        Log.d(TAG, "setAll age="+age + " height="+height + " name=" + name);
        this.age = age;
        this.height = height;
        this.name = name;
    }
}
