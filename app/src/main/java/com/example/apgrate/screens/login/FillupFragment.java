package com.example.apgrate.screens.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.apgrate.R;
import com.example.apgrate.model.User;
import com.example.apgrate.utils.CommonUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class FillupFragment extends Fragment {

    private EditText etName, etSurename, etRegion, etSchool;
    private Button btSingUp;
    private LoginViewModel mViewModel;
    private FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null) {
            mActivity = getActivity();
        }
        mViewModel = ViewModelProviders.of(mActivity).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillinfo, container, false);

        etName = view.findViewById(R.id.et_name);
        etSurename = view.findViewById(R.id.et_surename);
        etRegion = view.findViewById(R.id.et_region);
        etSchool = view.findViewById(R.id.et_school);
        btSingUp = view.findViewById(R.id.bt_signup);

        setOnClickListeners();

        return view;
    }

    private void setOnClickListeners() {
        btSingUp.setOnClickListener(v -> checkAndRegisterNewUser());
    }

    private void checkAndRegisterNewUser() {
        String name = etName.getText().toString();
        String surename = etSurename.getText().toString();
        String region = etRegion.getText().toString();
        String school = etSchool.getText().toString();
        if (!name.isEmpty() && !surename.isEmpty() && !region.isEmpty() && !school.isEmpty()) {
            Log.d("Keyword: " , mViewModel.getKeyword().getValue());
            mViewModel.registerNewUser(new User(name, surename, region, school));
        } else {
            CommonUtils.makeShortToast(getContext(), "Please fill all fields.");
        }
    }
}
