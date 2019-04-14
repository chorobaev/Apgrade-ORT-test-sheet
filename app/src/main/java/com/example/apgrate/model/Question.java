package com.example.apgrate.model;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Question implements Serializable {

    private int id;
    private int correctAnswer;
    private double marks;

    public Question() {

    }

    public Question(int correctAnswer, int marks) {
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + "CA: " + correctAnswer + " Marks: " + marks;
    }
}
