package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apgrate.R;
import com.example.apgrate.helper.TestLayout;
import com.example.apgrate.model.MiniTest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    private TestLayout mTestLayout;
    private static final String TEXT_SIZE = "size_of_test";

    static TestFragment getInstance(int testSize) {
        Bundle bundle = new Bundle();
        bundle.putInt(TEXT_SIZE, testSize);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mini_test, container, false);

        mTestLayout = v.findViewById(R.id.tl_test);
        if (getArguments() != null) {
            mTestLayout.setSize(getArguments().getInt(TEXT_SIZE));
        }
        setOnClickListeners();

        return v;
    }

    private void setOnClickListeners() {
        mTestLayout.setOnAnswerClickListener((view, index) -> {
            Log.d("MylogViewId", "" +  view.getId());
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
