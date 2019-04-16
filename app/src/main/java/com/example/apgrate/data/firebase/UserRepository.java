package com.example.apgrate.data.firebase;

import android.util.Log;

import com.example.apgrate.data.FirebaseUserRepository;
import com.example.apgrate.helper.Common;
import com.example.apgrate.helper.FirebaseQueryLiveData;
import com.example.apgrate.model.TestResult;
import com.example.apgrate.model.User;
import com.example.apgrate.screens.ApgradeApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class UserRepository implements FirebaseUserRepository {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth userAuth = FirebaseAuth.getInstance();
    private OnCompleteListener<Void> onCompleteListener = task -> {
    };
    private OnCompleteListener<AuthResult> onCompleteAuthResultListener;

    public UserRepository() {

    }

    public UserRepository(OnCompleteListener<AuthResult> onCompleteListener) {
        onCompleteAuthResultListener = onCompleteListener;
    }

    @Override
    public void getCurrentUser(OnResultListener<User> resultListener) {
        getUserById(Common.getUidFromEmail(userAuth.getCurrentUser().getEmail()), resultListener);
    }

    @Override
    public void getUserById(String uid, OnResultListener<User> resultListener) {
        mDatabase.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.d("MylogCatchdUser" ,user.toString());
                resultListener.onResult(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public LiveData<DataSnapshot> getKeyValid(String key) {
        return new FirebaseQueryLiveData(mDatabase.child("keys").child(key));
    }

    @Override
    public LiveData<DataSnapshot> getTests() {
        return new FirebaseQueryLiveData(mDatabase.child("tests"));
    }

    @Override
    public LiveData<DataSnapshot> getTestById(String id) {
        return new FirebaseQueryLiveData(mDatabase.child("tests").child(id));
    }

    @Override
    public void registerUser(User user, String keyword) {
        userAuth.createUserWithEmailAndPassword(user.getUsername(), keyword).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveUser(keyword, user);
                makeKeywordActive(keyword);
                Log.d("My log", "Success");
            }
        }).addOnCompleteListener(onCompleteAuthResultListener);

    }

    private void saveUser(String uid, User user) {
        mDatabase.child("users").child(uid).setValue(user).addOnCompleteListener(onCompleteListener);
    }

    private void makeKeywordActive(String keyword) {
        mDatabase.child("keys").child(keyword).setValue(true);
    }

    @Override
    public void signInUser(String keyword) {
        userAuth.signInWithEmailAndPassword(keyword + "@apgrade.kg", keyword).addOnCompleteListener(onCompleteAuthResultListener);
    }

    @Override
    public void saveTestResults(TestResult testResult, OnCompleteListener<Void> onCompleteListener) {
        DatabaseReference results = mDatabase.child("results").push();
        results.setValue(testResult).addOnCompleteListener(onCompleteListener);
        mDatabase.child("users")
                .child(Common.getUidFromEmail(userAuth.getCurrentUser().getEmail()))
                .child("leftAttemptions").setValue(testResult.getUserLeftAttempts() - 1);
    }
}
