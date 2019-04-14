package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.model.TestResult;

import java.security.spec.ECField;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ResultFragment extends Fragment {

    private TextView tvResult;
    private Button btFinish;
    private TestViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);

        tvResult = view.findViewById(R.id.tv_test_result);
        btFinish = view.findViewById(R.id.bt_return_home);

        setOnClickListeners();
        setObservers();

        return view;
    }

    private void setOnClickListeners() {
        btFinish.setOnClickListener(view -> getActivity().finish());
    }

    private void setObservers() {
        mViewModel.getIsTestFinished().observe(this, isTestFinished -> {
            try {
                if (isTestFinished) {
                    String resultString = tvResult.getText().toString();
                    TestResult tres = mViewModel.countResult();
                    String result = String.format(resultString,
                            (int)tres.getMath1(), (int)tres.getMaxMath1(),
                            (int)tres.getMath2(), (int)tres.getMaxMath2(),
                            (int)tres.getLanguage1(), (int)tres.getMaxLanguage1(),
                            (int)tres.getLanguage2(), (int)tres.getMaxLanguage2(),
                            (int)tres.getLanguage3(), (int)tres.getMaxLanguage3());
                    tvResult.setText(result);
                }
            } catch (Exception e) {
                Log.d("MylogErrFrag", e.getMessage() + e.getStackTrace());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
    }
}
