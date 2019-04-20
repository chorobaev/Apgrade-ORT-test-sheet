package com.example.apgrate.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import androidx.annotation.NonNull;

@IgnoreExtraProperties
public class Test implements Serializable {

    public enum TestStatus {OPEN, CLOSED, PASSED}

    private String id;
    private String name;
    private TestStatus status;
    private ArrayList<MiniTest> tests;
    private Date createdAt;

    public Test() {
        this.name = "";
        this.id = "";
        this.status = TestStatus.OPEN;
        initTests();
    }

    public Test(String name) {
        this.name = name;
        this.id = "";
        this.status = TestStatus.OPEN;
        initTests();
    }

    public Test(String name, TestStatus status) {
        this.id = "";
        this.name = name;
        this.status = status;
        initTests();
    }

    private void initTests() {
        tests = new ArrayList<>();
        tests.add(null);
        tests.add(null);
        tests.add(null);
        tests.add(null);
        tests.add(null);
    }

    public Test(String name, TestStatus status, ArrayList<MiniTest> tests) {
        this.id = "";
        this.name = name;
        this.tests = tests;
        this.status = status;
    }

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MiniTest getMathFirst() {
        return tests.get(0);
    }

    public MiniTest getMathSecond() {
        return tests.get(1);
    }

    public MiniTest getLanguageFirst() {
        return tests.get(2);
    }

    public MiniTest getLanguageSecond() {
        return tests.get(3);
    }

    public MiniTest getLanguageThird() {
        return tests.get(4);
    }

    public void setMathFirst(MiniTest test) {
        tests.set(0, test);
    }

    public void setMathSecond(MiniTest test) {
        tests.set(1, test);
    }

    public void setLanguageFirst(MiniTest test) {
        tests.set(2, test);
    }

    public void setLanguageSecond(MiniTest test) {
        tests.set(3, test);
    }

    public void setLanguageThird(MiniTest test) {
        tests.set(4, test);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Exclude
    public static Test getInstance(String name) {
        return new Test(name, Test.TestStatus.OPEN, getMiniTests());
    }

    @Exclude
    private static ArrayList<MiniTest> getMiniTests() {
        ArrayList<MiniTest> miniTest = new ArrayList<>();
        miniTest.add(new MiniTest(MiniTest.Category.MATH_1, getQuestions()));
        miniTest.add(new MiniTest(MiniTest.Category.MATH_2, getQuestions()));
        miniTest.add(new MiniTest(MiniTest.Category.LANGUAGE_1, getQuestions()));
        miniTest.add(new MiniTest(MiniTest.Category.LANGUAGE_2, getQuestions()));
        miniTest.add(new MiniTest(MiniTest.Category.LANGUAGE_3, getQuestions()));
        return miniTest;
    }

    @Exclude
    private static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = new Question(0, 0);
            question.setId(i);
            questions.add(question);
        }

        return questions;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                tests.get(0) + "\n" +
                tests.get(1) + "\n" +
                tests.get(2) + "\n" +
                tests.get(3) + "\n" +
                tests.get(4) + "\n";
    }
}
