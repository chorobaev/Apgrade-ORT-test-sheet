package com.example.apgrate.screens;

import android.app.Application;

import com.example.apgrate.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApgradeApp extends Application {

    private static User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().getReference().child("users").keepSynced(true);
        FirebaseDatabase.getInstance().getReference().child("tests").keepSynced(true);
        FirebaseDatabase.getInstance().getReference().child("results").keepSynced(true);
    }

    public User getCurrentUser() throws NullPointerException{
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
