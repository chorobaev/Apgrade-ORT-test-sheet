package com.example.apgrate.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.apgrate.R;
import com.example.apgrate.data.room.AppDatabase;
import com.example.apgrate.data.room.TestStateDao;
import com.example.apgrate.model.TestState;
import com.example.apgrate.utils.CommonUtils;
import com.example.apgrate.utils.Tester;

public class RoomTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_data").allowMainThreadQueries().build();

        TestStateDao testStateDao = db.testStateDao();

        TestState state = Tester.getDummyTestStateInstance();
        Log.d("MylogTestStateBef", state.toString());
        testStateDao.insertTestState(state);

        String res = testStateDao.getTestState(0).toString();
        CommonUtils.makeShortToast(this, res);
        Log.d("MylogTestState", res);
    }
}
