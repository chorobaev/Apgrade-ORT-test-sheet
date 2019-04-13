package com.example.apgrate.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@IgnoreExtraProperties
public class Test {

    public enum TestStatus {OPEN, CLOSED, PASSED}

    private String id;
    private String name;
    private TestStatus status;
    private HashMap<MiniTest.Category, MiniTest> tests;
    private Date createdAt;

    public Test(String name) {
        this.name = name;
        this.id = "";
        this.status = TestStatus.OPEN;
        this.tests = new HashMap<>();
    }

    public Test(String name, TestStatus status) {
        this.id = "";
        this.name = name;
        this.status = status;
        this.tests = new HashMap<>();
    }

    public Test(String name, TestStatus status, HashMap<MiniTest.Category, MiniTest> tests) {
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
        return tests.get(MiniTest.Category.MATH_1);
    }

    public MiniTest getMathSecond() {
        return tests.get(MiniTest.Category.MATH_2);
    }

    public MiniTest getLanguageFirst() {
        return tests.get(MiniTest.Category.LANGUAGE_1);
    }

    public MiniTest getLanguageSecond() {
        return tests.get(MiniTest.Category.LANGUAGE_2);
    }

    public MiniTest getLanguageThird() {
        return tests.get(MiniTest.Category.LANGUAGE_3);
    }

    public void getMathFirst(MiniTest test) {
        tests.put(MiniTest.Category.MATH_1, test);
    }

    public void getMathSecond(MiniTest test) {
        tests.put(MiniTest.Category.MATH_2, test);
    }

    public void getLanguageFirst(MiniTest test) {
        tests.put(MiniTest.Category.LANGUAGE_1, test);
    }

    public void getLanguageSecond(MiniTest test) {
        tests.put(MiniTest.Category.LANGUAGE_2, test);
    }

    public void getLanguageThird(MiniTest test) {
        tests.put(MiniTest.Category.LANGUAGE_3, test);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static Test getInstance(String name) {
        return new Test(name, Test.TestStatus.OPEN, getMiniTests());
    }

    private static HashMap<MiniTest.Category, MiniTest> getMiniTests() {
        HashMap<MiniTest.Category, MiniTest> miniTestHashMap = new HashMap<>();
        miniTestHashMap.put(MiniTest.Category.MATH_1, new MiniTest(MiniTest.Category.MATH_1, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.MATH_2, new MiniTest(MiniTest.Category.MATH_2, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_1, new MiniTest(MiniTest.Category.LANGUAGE_1, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_2, new MiniTest(MiniTest.Category.LANGUAGE_2, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_3, new MiniTest(MiniTest.Category.LANGUAGE_3, getQuestions()));
        return miniTestHashMap;
    }

    private static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = new Question(-1, -1);
            question.setId(i);
            questions.add(question);
        }

        return questions;
    }
}
