package com.flaterlab.apgrade.screens.home;

import com.flaterlab.apgrade.data.FirebaseUserRepository.OnResultListener;
import com.flaterlab.apgrade.data.firebase.UserRepository;
import com.flaterlab.apgrade.model.Test;
import com.flaterlab.apgrade.model.User;
import com.flaterlab.apgrade.utils.BaseViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

class MainViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<Test>> tests = new MutableLiveData<>();
    private LiveData<DataSnapshot> ratings = new MutableLiveData<>();
    private MutableLiveData<User> currentUser = new MutableLiveData<>();
    private UserRepository userRepo = new UserRepository();

    void init() {
        ratings = getSortedRatings();
    }

    void fetchCurrentUser() {
        userRepo.getCurrentUser(user -> currentUser.setValue(user));
    }

    void setCurrentUser(User user) {
        currentUser.setValue(user);
    }

    void setTests(ArrayList<Test> tests) {
        this.tests.setValue(tests);
    }

    LiveData<ArrayList<Test>> getTests() {
        return tests;
    }

    LiveData<DataSnapshot> getRatings() {
        return ratings;
    }

    LiveData<DataSnapshot> getTestsSnap() {
        return userRepo.getTests();
    }

    LiveData<User> getCurrentUser() {
        return currentUser;
    }

    private LiveData<DataSnapshot> getSortedRatings() {
        return userRepo.getSortedRatings();
    }

    void getCurrentUser(OnResultListener<User> resultListener) {
        userRepo.getCurrentUser(resultListener);
    }
}
