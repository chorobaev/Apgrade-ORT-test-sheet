package com.example.apgrate.model;

import com.example.apgrate.data.room.Converter;

import androidx.annotation.NonNull;

public class TestState {

    private int id;
    private int[] math1;
    private int[] math2;
    private int[] language1;
    private int[] language2;
    private int[] language3;
    private int currentTestIndex;
    private int leftTime;


    public TestState()
    {
        id = 0;
        currentTestIndex = 0;
        leftTime = 20;
        math1 = new int[31];
        math2 = new int[31];
        language1 = new int[36];
        language2 = new int[31];
        language3 = new int[31];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getMath1() {
        return math1;
    }

    public void setMath1(int[] math1) {
        this.math1 = math1;
    }

    public int[] getMath2() {
        return math2;
    }

    public void setMath2(int[] math2) {
        this.math2 = math2;
    }

    public int[] getLanguage1() {
        return language1;
    }

    public void setLanguage1(int[] language1) {
        this.language1 = language1;
    }

    public int[] getLanguage2() {
        return language2;
    }

    public void setLanguage2(int[] language2) {
        this.language2 = language2;
    }

    public int[] getLanguage3() {
        return language3;
    }

    public void setLanguage3(int[] language3) {
        this.language3 = language3;
    }

    public int getCurrentTestIndex() {
        return currentTestIndex;
    }

    public void setCurrentTestIndex(int currentTestIndex) {
        this.currentTestIndex = currentTestIndex;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Math1: ");
        s.append(Converter.fromIntArray(math1));
        s.append("\n");

        s.append("Math3: ");
        s.append(Converter.fromIntArray(math2));
        s.append("\n");

        s.append("Language1: ");
        s.append(Converter.fromIntArray(language1));
        s.append("\n");

        s.append("Language2: ");
        s.append(Converter.fromIntArray(language2));
        s.append("\n");

        s.append("Language3: ");
        s.append(Converter.fromIntArray(language3));
        s.append("\n");

        return "Id: " + id + "\n" + "Index: " + currentTestIndex + "\nLeft time: " + leftTime + "\n" + s.toString();
    }
}
