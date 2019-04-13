package com.example.apgrate.screens.test;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.apgrate.model.MiniTest;
import com.example.apgrate.model.Test;
import com.example.apgrate.utils.BaseViewModel;
import com.example.apgrate.utils.CommonUtils;

import java.util.Timer;
import java.util.TimerTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends BaseViewModel {

    private MutableLiveData<Test> currentTest = new MutableLiveData<>();
    private MutableLiveData<Test> actualTest = new MutableLiveData<>();
    private MutableLiveData<MiniTest.Category> currentTestCategory = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTestFinished = new MutableLiveData<>();
    private MutableLiveData<Integer> leftTime = new MutableLiveData<>();
    private CommonUtils.TestTimer timer = new CommonUtils.TestTimer();
    private MutableLiveData<Boolean> isBreakTime = new MutableLiveData<>();

    public void init() {
        currentTest.setValue(Test.getInstance("Current test"));
        currentTestCategory.setValue(MiniTest.Category.MATH_1);
        isTestFinished.setValue(false);
        isBreakTime.setValue(true);

        CommonUtils.OnTimerListener listener = new CommonUtils.OnTimerListener() {
            @Override
            public void run(int time) {
                leftTime.postValue(time);
            }

            @Override
            public void timeOver() {
                //TODO: time is over finish the current test
                nextTest();
            }
        };

        timer.setOnTimerListener(listener);
    }

    public void setTimer(int seconds) {
        timer.setNewTimer(seconds);
    }

    public void nextTest() {
        if (isBreakTime.getValue()) {
            if (!isTestFinished.getValue()) {
                isBreakTime.postValue(false);
            }
        } else {ad
            startNextCategory();
            isBreakTime.postValue(true);
        }
    }

    private void startNextCategory() {
        MiniTest.Category category = currentTestCategory.getValue();
        switch (category) {
            case MATH_1:
                currentTestCategory.postValue(MiniTest.Category.MATH_2);
                break;
            case MATH_2:
                currentTestCategory.postValue(MiniTest.Category.LANGUAGE_1);
                break;
            case LANGUAGE_1:
                currentTestCategory.postValue(MiniTest.Category.LANGUAGE_2);
                break;
            case LANGUAGE_2:
                currentTestCategory.postValue(MiniTest.Category.LANGUAGE_3);
                break;
            case LANGUAGE_3:
                isTestFinished.postValue(true);
                break;
        }
    }

    public void chooseAnswer(int testIndex, int answer) {
        MiniTest.Category category = currentTestCategory.getValue();
        Test test = currentTest.getValue();
        Log.d("MylogCatAndTest", "" + test + category);
        switch (category) {
            case MATH_1:
                test.getMathFirst().getQuestions().get(testIndex).setCorrectAnswer(answer);
                break;
            case MATH_2:
                test.getMathSecond().getQuestions().get(testIndex).setCorrectAnswer(answer);
                break;
            case LANGUAGE_1:
                test.getLanguageFirst().getQuestions().get(testIndex).setCorrectAnswer(answer);
                break;
            case LANGUAGE_2:
                test.getLanguageSecond().getQuestions().get(testIndex).setCorrectAnswer(answer);
                break;
            case LANGUAGE_3:
                test.getLanguageThird().getQuestions().get(testIndex).setCorrectAnswer(answer);
                break;
        }
    }

    public void countResult() {
        // TODO: count the result.
    }

    public LiveData<MiniTest.Category> getCurrentTestCategory() {
        return currentTestCategory;
    }

    public LiveData<Boolean> getIsTestFinished() {
        return isTestFinished;
    }

    public LiveData<Integer> getTimer() {
        return leftTime;
    }

    public LiveData<Boolean> getIsBreakTime() {
        return isBreakTime;
    }
}
