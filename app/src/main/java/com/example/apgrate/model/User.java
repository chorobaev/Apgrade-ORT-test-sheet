package com.example.apgrate.model;

import java.util.ArrayList;
import java.util.Date;

public class User {

    private String uid;
    private String firstName;
    private String sureName;
    private String region;
    private String school;
    private String username;
    private int leftAttemts = 3;
    private ArrayList<Integer> testIds;
    private Date createdAt;

    public User(String firstName, String sureName) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.username = firstName + "." + sureName + "@apgrade.kg";
        this.createdAt = new Date();
    }

    public User(String firstName, String sureName, String region, String school) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.region = region;
        this.school = school;
        this.username = firstName + "." + sureName + "@apgrade.kg";
        this.createdAt = new Date();
    }

    public int getLeftAttemtions() {
        return leftAttemts;
    }

    public void setLeftAttemtions(int leftAttemtions) {
        this.leftAttemts = leftAttemtions;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Integer> getTests() {
        return testIds;
    }

    public void setTests(ArrayList<Integer> tests) {
        this.testIds = tests;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
