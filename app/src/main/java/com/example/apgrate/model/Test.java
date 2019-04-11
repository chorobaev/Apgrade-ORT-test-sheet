package com.example.apgrate.model;

import java.util.Date;
import java.util.HashMap;

public class Test {

    private int id;
    private String name;
    private HashMap<MiniTest.Category, MiniTest> tests;
    private Date createdAt;

    public Test(String name, HashMap<MiniTest.Category, MiniTest> tests) {
        this.name = name;
        this.tests = tests;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
