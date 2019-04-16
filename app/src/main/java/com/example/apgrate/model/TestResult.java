package com.example.apgrate.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TestResult {

    private double math1;
    private double math2;
    private double language1;
    private double language2;
    private double language3;
    private double maxMath1;
    private double maxMath2;
    private double maxLanguage1;
    private double maxLanguage2;
    private double maxLanguage3;

    private String userId;
    private int userLeftAttempts;

    @Exclude
    public double getAll() {
        return language1 + language2 + language3 + math1 + math2;
    }

    @Exclude
    public double getMaxAll() {
        return maxLanguage1 + maxLanguage2 + maxLanguage3 + maxMath1 + maxMath2;
    }

    public double getMath1() {
        return math1;
    }

    public void setMath1(double math1) {
        this.math1 = math1;
    }

    public double getMath2() {
        return math2;
    }

    public void setMath2(double math2) {
        this.math2 = math2;
    }

    public double getLanguage1() {
        return language1;
    }

    public void setLanguage1(double language1) {
        this.language1 = language1;
    }

    public double getLanguage2() {
        return language2;
    }

    public void setLanguage2(double language2) {
        this.language2 = language2;
    }

    public double getLanguage3() {
        return language3;
    }

    public void setLanguage3(double language3) {
        this.language3 = language3;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getMaxMath1() {
        return maxMath1;
    }

    public void setMaxMath1(double maxMath1) {
        this.maxMath1 = maxMath1;
    }

    public double getMaxMath2() {
        return maxMath2;
    }

    public void setMaxMath2(double maxMath2) {
        this.maxMath2 = maxMath2;
    }

    public double getMaxLanguage1() {
        return maxLanguage1;
    }

    public void setMaxLanguage1(double maxLanguage1) {
        this.maxLanguage1 = maxLanguage1;
    }

    public double getMaxLanguage2() {
        return maxLanguage2;
    }

    public void setMaxLanguage2(double maxLanguage2) {
        this.maxLanguage2 = maxLanguage2;
    }

    public double getMaxLanguage3() {
        return maxLanguage3;
    }

    public void setMaxLanguage3(double maxLanguage3) {
        this.maxLanguage3 = maxLanguage3;
    }

    public int getUserLeftAttempts() {
        return userLeftAttempts;
    }

    public void setUserLeftAttempts(int userLeftAttempts) {
        this.userLeftAttempts = userLeftAttempts;
    }
}
