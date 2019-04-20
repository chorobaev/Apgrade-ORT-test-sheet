package com.example.apgrate.data.room;

import com.example.apgrate.model.TestState;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TestState.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TestStateDao testStateDao();
}
