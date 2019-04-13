package com.example.apgrate.model;

public class Question {

    private int id;
    private int correctAnswer;

    public Question(int correctAnswer, int marks) {
        this.correctAnswer = correctAnswer;
        this.marks = marks;
    }

    private int marks;

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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
