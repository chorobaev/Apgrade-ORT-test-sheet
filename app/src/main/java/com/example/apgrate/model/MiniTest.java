package com.example.apgrate.model;

import java.util.ArrayList;

public class MiniTest {

    public enum Category {MATH_1, MATH_2, LANGUAGE_1, LANGUAGE_2, LANGUAGE_3}
    private int id;
    private Category category;
    private ArrayList<Question> questions;

    public MiniTest(Category category, ArrayList<Question> questions) {
        this.category = category;
        this.questions = questions;
    }

    public int getSize() {
        return questions.size();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
