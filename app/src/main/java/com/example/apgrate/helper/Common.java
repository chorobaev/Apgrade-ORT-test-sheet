package com.example.apgrate.helper;

import android.util.Log;

import com.example.apgrate.model.MiniTest;
import com.example.apgrate.model.Question;
import com.example.apgrate.model.Test;
import com.example.apgrate.model.TestResult;

import java.util.ArrayList;

public class Common {

    public static String getTimeFormat(int seconds) {
        StringBuilder s = new StringBuilder();
        int min = seconds / 60;
        int sec = seconds % 60;
        if (min > 9) {
            s.append(min);
        } else  {
            s.append('0');
            s.append(min);
        }
        s.append(':');

        if (sec > 9) {
            s.append(sec);
        } else {
            s.append('0');
            s.append(sec);
        }

        return s.toString();
    }

    public static TestResult countTestResult(Test currentTest, Test actualTest) {
        TestResult result = new TestResult();
        Log.d("MylogActual", actualTest.toString() + actualTest.getMathFirst());
        result.setMath1(compareAndCount(currentTest.getMathFirst(), actualTest.getMathFirst()));
        result.setMath1(compareAndCount(currentTest.getMathSecond(), actualTest.getMathSecond()));
        result.setMath1(compareAndCount(currentTest.getLanguageFirst(), actualTest.getLanguageFirst()));
        result.setMath1(compareAndCount(currentTest.getLanguageSecond(), actualTest.getLanguageSecond()));
        result.setMath1(compareAndCount(currentTest.getLanguageThird(), actualTest.getLanguageThird()));


        result.setMaxMath1(actualTest.getMathFirst().getMaxMarks());
        result.setMaxMath2(actualTest.getMathSecond().getMaxMarks());
        result.setLanguage1(actualTest.getLanguageFirst().getMaxMarks());
        result.setLanguage2(actualTest.getLanguageSecond().getMaxMarks());
        result.setLanguage3(actualTest.getLanguageThird().getMaxMarks());

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
}
