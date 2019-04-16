package com.example.apgrate.data;

import com.example.apgrate.model.User;

public interface FirebaseAuthManager {

    void registerUser(User user, String keyword);
    void signInUser(String keyword);
}
