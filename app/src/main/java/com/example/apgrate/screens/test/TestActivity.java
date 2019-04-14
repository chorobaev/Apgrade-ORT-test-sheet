package com.example.apgrate.screens.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.helper.Common;
import com.example.apgrate.model.MiniTest;
import com.example.apgrate.model.Test;
import com.example.apgrate.utils.CommonUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class TestActivity extends AppCompatActivity {

    private TestViewModel mViewModel;
    private FragmentManager mFragmentManager;
    private TestFragment mTestFragment;
    private TextView tvCategory;
    private TextView tvTimer;
    private FloatingActionButton fabNext;
    private Test testchik;

    public static final String ACTUAL_TEST = "ACTUAL_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        setSupportActionBar(findViewById(R.id.tb_test));

        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mViewModel.init();

        tvCategory = findViewById(R.id.tv_test_category);
        tvTimer = findViewById(R.id.tv_timer);
        fabNext = findViewById(R.id.fab_next);

        mFragmentManager = getSupportFragmentManager();
        mTestFragment = TestFragment.getInstance(30);

        testchik = (Test) getIntent().getSerializableExtra(ACTUAL_TEST);
        Log.d("MylogTest", testchik.toString());
        //Log.d("MylogTestId", testchik.toString());
        mViewModel.setActualTest(testchik);

        setObservers();
        setOnClickListeners();
    }

    private void setObservers() {
        mViewModel.getCurrentTestCategory().observe(this, this::setTitleCategory);

        //mViewModel.getTestSnap(testId).observe(this, testSnap -> {
            //Test test = testSnap.getValue(Test.class);
            //if (test != null) {
            //    Log.d("MylogTest", test.toString());
            //    mViewModel.setActualTest(test);
            // }
 //       });
        mViewModel.getIsTestFinished().observe(this, isTestFinished -> {
            if (isTestFinished) {
                mFragmentManager.beginTransaction().replace(R.id.fl_test_container, new ResultFragment()).commit();
            }
        });

        mViewModel.getTimer().observe(this, time -> {
            tvTimer.setText(Common.getTimeFormat(time));
        });

        mViewModel.getIsBreakTime().observe(this, isBreakTime -> {
            if (isBreakTime) {
                tvCategory.setText(getResources().getString(R.string.test_category_break_time));
                mFragmentManager.beginTransaction().remove(mTestFragment).commit();
            } else {
                mFragmentManager.beginTransaction().add(R.id.fl_test_container, mTestFragment).commit();
            }
            mViewModel.startNextSection();
        });
    }

    private void setTitleCategory(MiniTest.Category category) {
        switch (category) {
            case MATH_1:
                tvCategory.setText(getResources().getString(R.string.test_category_math1));
                break;
            case MATH_2:
                tvCategory.setText(getResources().getString(R.string.test_category_math2));
                break;
            case LANGUAGE_1:
                tvCategory.setText(getResources().getString(R.string.test_category_language1));
                break;
            case LANGUAGE_2:
                tvCategory.setText(getResources().getString(R.string.test_category_language2));
                break;
            case LANGUAGE_3:
                tvCategory.setText(getResources().getString(R.string.test_category_language3));
                break;
        }
    }

    private void setOnClickListeners() {
        fabNext.setOnClickListener(view -> {
            mViewModel.forceToStartNextSection();
        });
    }
}
