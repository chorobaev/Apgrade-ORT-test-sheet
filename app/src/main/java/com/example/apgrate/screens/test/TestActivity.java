package com.example.apgrate.screens.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.utils.CommonUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestActivity extends AppCompatActivity {

    private TestViewModel mViewModel;
    private FragmentManager mFragmentManager;
    private TestFragment mTestFragment;
    private TextView tvCategory;
    private TextView tvTimer;
    private FloatingActionButton fabNext;

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

        setObservers();
        setOnClickListeners();
    }

    private void setObservers() {
        mViewModel.getCurrentTestCategory().observe(this, category -> {
            tvCategory.setText(category.toString());
        });

        mViewModel.getIsTestFinished().observe(this, isTestFinished -> {
            mViewModel.countResult();
            // TODO: show results fragment
        });

        mViewModel.getTimer().observe(this, time -> {
            tvTimer.setText(CommonUtils.getTimeFormat(time));
        });

        mViewModel.getIsBreakTime().observe(this, isBreakTime -> {
            if (isBreakTime) {
                mFragmentManager.beginTransaction().remove(mTestFragment).commit();
                mViewModel.setTimer(3);
            } else {
                mFragmentManager.beginTransaction().add(R.id.fl_test_container, mTestFragment).commit();
                mViewModel.setTimer(5);
            }
        });
    }

    private void setOnClickListeners() {
        fabNext.setOnClickListener(view -> {
            mViewModel.nextTest();
        });
    }
}
