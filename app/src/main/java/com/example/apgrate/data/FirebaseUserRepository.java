package com.example.apgrate.data;

import com.example.apgrate.model.TestResult;
import com.example.apgrate.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;

import androidx.lifecycle.LiveData;

public interface FirebaseUserRepository {

    void getCurrentUser(OnResultListener<User> resultListener);
    void getUserById(String uid, OnResultListener<User> resultListener);
    LiveData<DataSnapshot> getKeyValid(String key);
    LiveData<DataSnapshot> getTests();
    LiveData<DataSnapshot> getTestById(String id);
    void registerUser(User user, String keyword);
    void signInUser(String keyword);
    void saveTestResults(TestResult testResult, OnCompleteListener<Void> onCompleteListener);

    interface OnResultListener<M> {
        void onResult(M result);
    }
}
