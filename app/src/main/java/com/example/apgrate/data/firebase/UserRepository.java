package com.example.apgrate.data.firebase;

import android.util.Log;

import com.example.apgrate.data.FirebaseUserRepository;
import com.example.apgrate.helper.FirebaseQueryLiveData;
import com.example.apgrate.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
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
    private OnCompleteListener onCompleteListener = task -> {
        if (task.isCanceled()) {
            Log.e("My error", "error");
        }
        Log.d("My log", "Success");
    };

    @Override
    public LiveData<User> getCurrentUser() {
        return null;
    }

    @Override
    public LiveData<User> getUserById(int uid) {
        return null;
    }

    @Override
    public LiveData<DataSnapshot> getKeyValid(String key) {
        return new FirebaseQueryLiveData(mDatabase.child("keys").child(key));
    }

    @Override
    public void registerUser(User user, String keyword) {
        userAuth.createUserWithEmailAndPassword(user.getUsername(), keyword).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveUser(keyword, user);
                makeKeywordActive(keyword);
                Log.d("My log", "Success");
            }
            Log.d("My log", "Success not");
        });

    }

    private void saveUser(String uid, User user) {
        mDatabase.child("users").child(uid).setValue(user).addOnCompleteListener(onCompleteListener);
    }

    private void makeKeywordActive(String keyword) {
        mDatabase.child("keys").child(keyword).setValue(true);
    }

    @Override
    public void signInUser(String keyword) {
        mDatabase.child("users").child(keyword).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userAuth.signInWithEmailAndPassword(user.getUsername(), keyword).addOnCompleteListener(onCompleteListener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Mylog", "Something went wrong while logging in");
            }
        });
    }
}
