package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.model.TestResult;
import com.example.apgrate.utils.CommonUtils;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ResultFragment extends Fragment {

    private TextView tvResult;
    private TestViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);

        tvResult = view.findViewById(R.id.tv_test_result);

        setObservers();

        return view;
    }

    private void setObservers() {
        mViewModel.getIsTestFinished().observe(this, isTestFinished -> {
            try {
                if (isTestFinished) {
                    String resultString = tvResult.getText().toString();
                    TestResult tres = mViewModel.countResult();
                    saveTestResults(tres);
                    String result = String.format(resultString,
                            (int)tres.getMath1(), (int)tres.getMaxMath1(),
                            (int)tres.getMath2(), (int)tres.getMaxMath2(),
                            (int)tres.getLanguage1(), (int)tres.getMaxLanguage1(),
                            (int)tres.getLanguage2(), (int)tres.getMaxLanguage2(),
                            (int)tres.getLanguage3(), (int)tres.getMaxLanguage3(),
                            (int)tres.getAll(), (int)tres.getMaxAll());
                    tvResult.setText(result);
                }
            } catch (Exception e) {
                Log.d("MylogErrFrag", e.getMessage());
            }
        });
    }

    private void saveTestResults(TestResult testResult) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        uid = uid.substring(0, uid.indexOf('@'));
        testResult.setUserId(uid);
        mViewModel.saveTestResults(testResult, task -> {
            String resultMsg = task.isSuccessful() ? getResources().getString(R.string.toast_test_result_saving_success)
                    : getResources().getString(R.string.toast_test_result_saving_failed);

            CommonUtils.makeLongToast(getContext(), resultMsg);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
    }
}
