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

    private TextView tvMath1, tvMath2, tvLang1, tvLang2, tvLang3, tvAll, tvUser;
    private TestViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);

        tvMath1 = view.findViewById(R.id.tv_result_math1);
        tvMath2 = view.findViewById(R.id.tv_result_math2);
        tvLang1 = view.findViewById(R.id.tv_result_language1);
        tvLang2 = view.findViewById(R.id.tv_result_language2);
        tvLang3 = view.findViewById(R.id.tv_result_language3);
        tvAll = view.findViewById(R.id.tv_result_all);
        tvUser = view.findViewById(R.id.tv_user_full_name);

        setObservers();

        return view;
    }

    private void setObservers() {
        mViewModel.getIsTestFinished().observe(this, isTestFinished -> {
            try {
                if (isTestFinished) {
                    TestResult tres = mViewModel.countResult();

                    saveTestResults(tres);
                    tvMath1.setText(String.valueOf((int) tres.getMath1()));
                    tvMath2.setText(String.valueOf((int) tres.getMath2()));
                    tvLang1.setText(String.valueOf((int) tres.getLanguage1()));
                    tvLang2.setText(String.valueOf((int) tres.getLanguage2()));
                    tvLang3.setText(String.valueOf((int) tres.getLanguage3()));
                    tvAll.setText(String.valueOf((int) tres.getTotalMarks()));
                    tvUser.setText(tres.getUserFullName());
                }
            } catch (Exception e) {
                Log.d("MylogErrFrag", e.getMessage());
            }
        });

        mViewModel.getIsResultSaved().observe(this, isResultSaved -> {
            if (isResultSaved != null) {
                String resultMsg = isResultSaved ? getResources().getString(R.string.toast_test_result_saving_success)
                        : getResources().getString(R.string.toast_test_result_saving_failed);
                CommonUtils.makeLongToast(getActivity(), resultMsg);
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

        mViewModel.saveTestResults(testResult);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
    }
}
