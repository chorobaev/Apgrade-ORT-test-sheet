package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.helper.Common;
import com.example.apgrate.model.TestResult;
import com.example.apgrate.model.User;
import com.example.apgrate.screens.ApgradeApp;
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
                            (int)tres.getTotalMarks(), (int)tres.getMaxTotalMarks());
                    tvResult.setText(result);
                }
            } catch (Exception e) {
                Log.d("MylogErrFrag", e.getMessage());
            }
        });
    }

    private void saveTestResults(TestResult testResult) {
        ApgradeApp app = ((ApgradeApp) getContext().getApplicationContext());
        User user = app.getCurrentUser();
        int leftAttempts = user.getLeftAttemptions();
        testResult.setUserLeftAttempts(leftAttempts);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        testResult.setUserId(Common.getUidFromEmail(email));

        String fullName = user.getFirstname() + " " + user.getSurname();
        testResult.setUserFullName(fullName);

        String extraInfo = user.getRegion() + ", " + user.getSchool();
        testResult.setRegionAndSchool(extraInfo);

        mViewModel.saveTestResults(testResult, task -> {
            String resultMsg = task.isSuccessful() ? getResources().getString(R.string.toast_test_result_saving_success)
                    : getResources().getString(R.string.toast_test_result_saving_failed);

            CommonUtils.makeLongToast(getActivity(), resultMsg);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
    }
}
