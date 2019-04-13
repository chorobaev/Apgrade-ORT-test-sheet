package com.example.apgrate.screens.login;

import android.util.Log;

import com.example.apgrate.data.FirebaseUserRepository;
import com.example.apgrate.data.firebase.UserRepository;
import com.example.apgrate.model.User;
import com.example.apgrate.utils.BaseViewModel;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> keyword = new MutableLiveData<>();;
    private final UserRepository userRepository = new UserRepository();

    void setKeyword(String keyword) {
        this.keyword.setValue(keyword);
    }

    LiveData<DataSnapshot> getKeyValidation() {
        return userRepository.getKeyValid(keyword.getValue());
    }

    LiveData<String> getKeyword() {
    return keyword;
    }

    void registerNewUser(User user) {
        Log.d("My log",  "hhhh");
        user.setUid(keyword.getValue());
        user.setUsername(keyword.getValue() + "@apgrade.kg");
        userRepository.registerUser(user, keyword.getValue());
    }

    void signInUser() {
        userRepository.signInUser(keyword.getValue());
    }
}
