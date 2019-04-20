package com.example.apgrate.screens;

import android.app.Application;
import android.util.Log;

import com.example.apgrate.data.room.AppDatabase;
import com.example.apgrate.model.TestState;
import com.example.apgrate.model.User;
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

        //mDatabase = Room.databaseBuilder(this, AppDatabase.class, "test_state_db").allowMainThreadQueries().build();

        //TestState testState = mDatabase.testStateDao().getTestState(0);
        //Log.d("MylogSavedTestState", (testState == null ? null : testState.toString()) + "");
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
