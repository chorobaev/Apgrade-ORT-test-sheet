package com.example.apgrate.screens.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.utils.CommonUtils;
import com.google.firebase.database.DataSnapshot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class SignupFragment extends Fragment {

    private TextView tvTryForFree;
    private TextView tvGetKey;
    private Button btNext;
    private EditText etKeyword;
    private LoginViewModel mViewModel;
    private FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            mActivity = getActivity();
        } else {
            throw new NullPointerException("Fragment is not hosted");
        }
        mViewModel = ViewModelProviders.of(mActivity).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        tvTryForFree = view.findViewById(R.id.tv_try_for_free);
        tvGetKey = view.findViewById(R.id.tv_get_keyword_clickable);
        btNext = view.findViewById(R.id.bt_next);
        etKeyword = view.findViewById(R.id.et_keyword);

        setOnClickListeners();

        return view;
    }

    private void setOnClickListeners() {
        tvTryForFree.setOnClickListener(view -> {

        });

        tvGetKey.setOnClickListener(view -> {

        });

        btNext.setOnClickListener(view -> {
            String key = etKeyword.getText().toString();
            if (key.isEmpty()) {
                CommonUtils.makeLongToast(getContext(), getResources().getString(R.string.toast_empty_key));
            } else {
                checkKeyword();
            }
        });
    }

    private void checkKeyword() {
        mViewModel.setKeyword(etKeyword.getText().toString());
        mViewModel.getKeyValidation().observe(this, data -> {
            if (data != null && data.exists()) {
                signInOrRegister(data);
            } else {
                CommonUtils.makeShortToast(getContext(), "Key is not valid");
            }
        });
    }

    private void signInOrRegister(DataSnapshot data) {
        if (data.getValue(Boolean.class)) {
            signInUser();
        } else {
            registerUser();
        }
    }

    private void signInUser() {
        mViewModel.signInUser();
    }

    private void registerUser() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new FillupFragment()).commit();
        }
    }
}
