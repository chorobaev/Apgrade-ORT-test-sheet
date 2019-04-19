package com.example.apgrate.screens.home;

import com.example.apgrate.data.FirebaseUserRepository.OnResultListener;
import com.example.apgrate.data.firebase.UserRepository;
import com.example.apgrate.model.Test;
import com.example.apgrate.model.TestResult;
import com.example.apgrate.model.User;
import com.example.apgrate.utils.BaseViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<Test>> tests = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TestResult>> ratings = new MutableLiveData<>();
    private UserRepository userRepo = new UserRepository();

    void init() {
        getSortedRatings(results -> {
            ratings.setValue(results);
        });
    }

    void setTests(ArrayList<Test> tests) {
        this.tests.setValue(tests);
    }

    LiveData<ArrayList<Test>> getTests() {
        return tests;
    }

    LiveData<ArrayList<TestResult>> getRatings() {
        return ratings;
    }

    LiveData<DataSnapshot> getTestsSnap() {
        return userRepo.getTests();
    }

    void getCurrentUser(OnResultListener<User> resultListener) {
        userRepo.getCurrentUser(resultListener);
    }

    private void getSortedRatings(OnResultListener<ArrayList<TestResult>> resultListener) {
        userRepo.getSortedRatings(resultListener);
    }
}
