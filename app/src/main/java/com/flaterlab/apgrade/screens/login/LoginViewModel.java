package com.flaterlab.apgrade.screens.login;

import com.flaterlab.apgrade.data.firebase.UserRepository;
import com.flaterlab.apgrade.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> keyword = new MutableLiveData<>();
    private UserRepository userRepository;

    void init(OnCompleteListener<AuthResult> onCompleteListener) {
        this.userRepository = new UserRepository(onCompleteListener);
    }

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
        user.setUid(keyword.getValue());
        user.setUsername(keyword.getValue() + "@apgrade.kg");
        userRepository.registerUser(user, keyword.getValue());
    }

    void signInUser() {
        userRepository.signInUser(keyword.getValue());
    }
}
