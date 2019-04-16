package com.example.apgrate.screens.home;

import com.example.apgrate.data.FirebaseUserRepository.OnResultListener;
import com.example.apgrate.data.firebase.UserRepository;
import com.example.apgrate.model.Test;
import com.example.apgrate.model.User;
import com.example.apgrate.utils.BaseViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<Test>> tests = new MutableLiveData<>();
    private UserRepository userRepo = new UserRepository();

    void init() {

    }

    void setTests(ArrayList<Test> tests) {
        this.tests.setValue(tests);
    }

    LiveData<ArrayList<Test>> getTests() {
        return tests;
    }

    LiveData<DataSnapshot> getTestsSnap() {
        return userRepo.getTests();
    }

    void getCurrenUser(OnResultListener<User> resultListener) {
        userRepo.getCurrentUser(resultListener);
    }
}
