package com.example.apgrate.utils;

import com.example.apgrate.model.MiniTest;
import com.example.apgrate.model.Question;
import com.example.apgrate.model.Test;
import com.example.apgrate.model.TestState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Tester {

    /*public static ArrayList<Test> getTests() {
        ArrayList<Test> tests = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            String name = "Test" + i;
            tests.add(getTest(name));
        }

        return tests;
    }*/

    /*public static Test getTest(String name) {
        return new Test(name, Test.TestStatus.OPEN, getMiniTests());
    }*/

    public static HashMap<MiniTest.Category, MiniTest> getMiniTests() {
        HashMap<MiniTest.Category, MiniTest> miniTestHashMap = new HashMap<>();
        miniTestHashMap.put(MiniTest.Category.MATH_1, new MiniTest(MiniTest.Category.MATH_1, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.MATH_2, new MiniTest(MiniTest.Category.MATH_2, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_1, new MiniTest(MiniTest.Category.LANGUAGE_1, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_2, new MiniTest(MiniTest.Category.LANGUAGE_2, getQuestions()));
        miniTestHashMap.put(MiniTest.Category.LANGUAGE_3, new MiniTest(MiniTest.Category.LANGUAGE_3, getQuestions()));
        return miniTestHashMap;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = new Question(-1, -1);
            question.setId(i);
            questions.add(question);
        }

        return questions;
    }

    public static TestState getDummyTestStateInstance() {
        TestState testState = new TestState();
        testState.setMath1(getMiniTest());
        testState.setMath2(getMiniTest());
        testState.setLanguage1(getMiniTest());
        testState.setLanguage2(getMiniTest());
        testState.setLanguage3(getMiniTest());
        return testState;
    }

    private static int[] getMiniTest() {
        int[] a = new int[31];
        for (int j = 0; j < 31; j++) {
            a[j] = j+1;
        }

        return a;
    }

}
