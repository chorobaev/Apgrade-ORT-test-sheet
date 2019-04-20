package com.example.apgrate.data.room;

import com.example.apgrate.model.TestState;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TestStateDao {

    @Query("SELECT * FROM teststate WHERE id LIKE :id")
    TestState getTestState(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTestState(TestState testState);
}
