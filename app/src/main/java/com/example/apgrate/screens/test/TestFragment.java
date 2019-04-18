package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apgrate.R;
import com.example.apgrate.helper.TestLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    private TestLayout mTestLayout;
    private static final String TEXT_SIZE = "size_of_test";

    static TestFragment getInstance() {
        return new TestFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mini_test, container, false);

        mTestLayout = v.findViewById(R.id.tl_test);
        setOnClickListeners();

        return v;
    }

    private void setOnClickListeners() {
        mTestLayout.setOnAnswerClickListener((view, index) -> {
            Log.d("MylogChosenAnswer", "" +  view.getId());
            mViewModel.chooseAnswer(index, view.getId());
        });
    }

    private void setObservers() {
        mViewModel.getCurrentTestCategory().observe(this, category -> {
            mTestLayout.reset();
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
        setObservers();
    }
}
