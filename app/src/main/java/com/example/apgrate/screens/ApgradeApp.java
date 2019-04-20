package com.example.apgrate.screens;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.apgrate.data.room.AppDatabase;
import com.example.apgrate.model.TestState;
import com.example.apgrate.model.User;
import com.example.apgrate.screens.introduction.IntroActivity;
import com.google.firebase.database.FirebaseDatabase;

import androidx.room.Room;

public class ApgradeApp extends Application {

    private static User currentUser;
    private static AppDatabase mDatabase;

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

    /*public AppDatabase getAppDatabase() {
        return mDatabase;
    }*/

    public static ApgradeApp getInstance() {
        return new ApgradeApp();
    }
}
