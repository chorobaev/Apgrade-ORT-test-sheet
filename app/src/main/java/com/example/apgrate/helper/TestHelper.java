package com.example.apgrate.helper;

import android.util.Log;

import com.example.apgrate.model.MiniTest;
import com.example.apgrate.model.Question;
import com.example.apgrate.model.Test;
import com.example.apgrate.model.TestResult;
import com.example.apgrate.model.TestState;

import java.util.ArrayList;

public class TestHelper {

    public static String SAVED_TEST_STATE = "com.example.apgrade.teststate";
    public static String IS_THERE_SAVED_TEST = "com.example.apgrade.teststate.isThereTest";

    public static TestResult countTestResult(Test currentTest, Test actualTest) {
        TestResult result = new TestResult();
        //Log.d("MylogActual", actualTest.toString() + actualTest.getMathFirst());
        result.setMath1(compareAndCount(currentTest.getMathFirst(), actualTest.getMathFirst()));
        result.setMath2(compareAndCount(currentTest.getMathSecond(), actualTest.getMathSecond()));
        result.setLanguage1(compareAndCount(currentTest.getLanguageFirst(), actualTest.getLanguageFirst()));
        result.setLanguage2(compareAndCount(currentTest.getLanguageSecond(), actualTest.getLanguageSecond()));
        result.setLanguage3(compareAndCount(currentTest.getLanguageThird(), actualTest.getLanguageThird()));


        result.setMaxMath1(actualTest.getMathFirst().getMaxMarks());
        result.setMaxMath2(actualTest.getMathSecond().getMaxMarks());
        result.setMaxLanguage1(actualTest.getLanguageFirst().getMaxMarks());
        result.setMaxLanguage2(actualTest.getLanguageSecond().getMaxMarks());
        result.setMaxLanguage3(actualTest.getLanguageThird().getMaxMarks());

        return result;
    }

    private static double compareAndCount(MiniTest current, MiniTest actual) {
        ArrayList<Question> answer = current.getQuestions();
        ArrayList<Question> keys = actual.getQuestions();
        double result = 0;
        for (int i = 0; i < answer.size(); i++) {
            if (answer.get(i).getCorrectAnswer() == keys.get(i).getCorrectAnswer()) {
                result += keys.get(i).getMarks();
            }
        }

        return result;
    }

    public static TestState fromTest(Test test) {
        TestState state = new TestState();

        state.setMath1(fromMiniTest(test.getMathFirst(), state.getMath1().length));
        state.setMath2(fromMiniTest(test.getMathSecond(), state.getMath2().length));
        state.setLanguage1(fromMiniTest(test.getLanguageFirst(), state.getLanguage1().length));
        state.setLanguage2(fromMiniTest(test.getLanguageSecond(), state.getLanguage2().length));
        state.setLanguage3(fromMiniTest(test.getLanguageThird(), state.getLanguage3().length));

        return state;
    }

    private static int[] fromMiniTest(MiniTest miniTes, int testSize) {
        ArrayList<Question> questions = miniTes.getQuestions();
        int[] result = new int[testSize];

        for (int i = 1; i < testSize; i++) {
            try {
                result[i] = questions.get(i).getCorrectAnswer();
            } catch (IndexOutOfBoundsException ignored) {
            }
        }

        return result;
    }
}
