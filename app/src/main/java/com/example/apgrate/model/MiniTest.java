package com.example.apgrate.model;

import java.util.ArrayList;

public class MiniTest {

    enum Category {MATH_1, MATH_2, LANGUAGE_1, LANGUAGE_2, LANGUAGE_3}
    private int id;
    private int category;
    private ArrayList<Question> questions;

    public MiniTest(int category, ArrayList<Question> questions) {
        this.category = category;
        this.questions = questions;
    }

    public int getSize() {
        return questions.size();
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
