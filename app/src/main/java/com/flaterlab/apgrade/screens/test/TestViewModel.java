package com.flaterlab.apgrade.screens.test;

import com.flaterlab.apgrade.data.firebase.UserRepository;
import com.flaterlab.apgrade.helper.TestHelper;
import com.flaterlab.apgrade.model.MiniTest;
import com.flaterlab.apgrade.model.Schedule;
import com.flaterlab.apgrade.model.Test;
import com.flaterlab.apgrade.model.TestResult;
import com.flaterlab.apgrade.utils.BaseViewModel;
import com.flaterlab.apgrade.utils.CommonUtils;
import com.google.firebase.database.DataSnapshot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends BaseViewModel {

    private UserRepository userRepo = new UserRepository();
    private MutableLiveData<Test> currentTest = new MutableLiveData<>();
    private MutableLiveData<Test> actualTest = new MutableLiveData<>();
    private MutableLiveData<MiniTest.Category> currentTestCategory = new MutableLiveData<>();
    private MutableLiveData<Boolean> isTestFinished = new MutableLiveData<>();
    private MutableLiveData<Integer> leftTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> isBreakTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> isResultSaved = new MutableLiveData<>();
    private CommonUtils.TestTimer timer = new CommonUtils.TestTimer();
    private Schedule schedule = new Schedule();

    void init() {
        currentTest.setValue(Test.getInstance("Current test"));
        currentTestCategory.setValue(MiniTest.Category.MATH_1);
        isTestFinished.setValue(false);
        isBreakTime.setValue(false);
        isResultSaved.setValue(null);

        CommonUtils.OnTimerListener listener = new CommonUtils.OnTimerListener() {
            @Override
            public void run(int time) {
                leftTime.postValue(time);
            }

            @Override
            public void timeOver() {
                //TODO: time is over finish the current test
                nextSection();
            }
        };

        timer.setOnTimerListener(listener);
    }

    void chooseAnswer(int testIndex, int answer) {
        MiniTest.Category category = currentTestCategory.getValue();
        Test test = currentTest.getValue();
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

    TestResult countResult() {
        return TestHelper.countTestResult(currentTest.getValue(), actualTest.getValue());
    }

    void saveTestResults(TestResult testResult) {
        userRepo.saveTestResults(testResult, task -> isResultSaved.setValue(task.isSuccessful()));
    }

    void startNextSection() {
        setTimer(schedule.getScheduleIndex());
        schedule.next();
    }

    void forceToStartNextSection() {
        timer.terminateTimer();
        nextSection();
    }

    void setActualTest(Test test) {
        actualTest.setValue(test);
    }

    private void setTimer(int seconds) {
        timer.setNewTimer(seconds);
    }

    private void nextSection() {
        if (isBreakTime.getValue()) {
            setNextTestCategory();
            isBreakTime.postValue(false);
        } else {
            if (!(currentTestCategory.getValue() == MiniTest.Category.LANGUAGE_3)) {
                isBreakTime.postValue(true);
            } else {
                isTestFinished.postValue(true);
            }
        }
    }

    private void setNextTestCategory() {
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

    LiveData<DataSnapshot> getTestSnap(String id) {
        return userRepo.getTestById(id);
    }

    LiveData<MiniTest.Category> getCurrentTestCategory() {
        return currentTestCategory;
    }

    LiveData<Boolean> getIsTestFinished() {
        return isTestFinished;
    }

    LiveData<Integer> getTimer() {
        return leftTime;
    }

    LiveData<Boolean> getIsBreakTime() {
        return isBreakTime;
    }

    LiveData<Boolean> getIsResultSaved() {
        return isResultSaved;
    }
}
