package com.example.apgrate.data;

import com.example.apgrate.model.User;
import com.google.firebase.database.DataSnapshot;

import androidx.lifecycle.LiveData;

public interface FirebaseUserRepository {

    LiveData<User> getCurrentUser();
    LiveData<User> getUserById(int uid);
    LiveData<DataSnapshot> getKeyValid(String key);
    void registerUser(User user, String keyword);
    void signInUser(String keyword);
}
