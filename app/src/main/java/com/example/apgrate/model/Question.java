package com.example.apgrate.model;

public class Question {

    private int id;
    private int order;
    private int correctAnswer;

    public Question(int order, int correctAnswer, int marks) {
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
