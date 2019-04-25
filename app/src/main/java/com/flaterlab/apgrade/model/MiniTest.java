package com.flaterlab.apgrade.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;

@IgnoreExtraProperties
public class MiniTest implements Serializable {

    public enum Category {MATH_1, MATH_2, LANGUAGE_1, LANGUAGE_2, LANGUAGE_3}
    private int id;
    private Category category;
    private ArrayList<Question> questions;

    public MiniTest() {

    }

    public MiniTest(Category category, ArrayList<Question> questions) {
        this.category = category;
        this.questions = questions;
    }

    @Exclude
    public double getMaxMarks() {
        double sum = 0;
        for (Question q : questions) {
            sum += q.getMarks();
        }

        return sum;
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

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Question q : questions) {
            s.append(q.toString());
            s.append(",\n ");
        }
        return "Category: " + category.toString() + " Qs: " + s.toString();
    }
}
