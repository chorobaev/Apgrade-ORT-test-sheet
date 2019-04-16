package com.example.apgrate.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;

@IgnoreExtraProperties
public class User {

    private String uid;
    private String firstname;
    private String surname;
    private String region;
    private String school;
    private String username;
    private int leftAttempts = 3;
    private ArrayList<Integer> testIds;
    private Date createdAt;

    public  User() {
    }

    public User(String firstName, String sureName) {
        this.firstname = firstName;
        this.surname = surname;
        this.username = firstName + "." + sureName + "@apgrade.kg";
        this.createdAt = new Date();
    }

    public User(String firstName, String sureName, String region, String school) {
        this.firstname = firstName;
        this.surname = sureName;
        this.region = region;
        this.school = school;
        this.username = firstName + "." + sureName + "@apgrade.kg";
        this.createdAt = new Date();
    }

    public int getLeftAttemptions() {
        return leftAttempts;
    }

    public void setLeftAttemptions(int leftAttemptions) {
        this.leftAttempts = leftAttemptions;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @NonNull
    @Override
    public String toString() {
        return firstname + " " + surname + "; User id: " + uid + "; Attempts left: " + leftAttempts;
    }
}
