package com.example.apgrate.screens.login;

import android.os.Bundle;
import android.util.Log;

import com.example.apgrate.R;
import com.example.apgrate.utils.BaseActivity;
import com.example.apgrate.utils.CommonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends BaseActivity {

    private LoginViewModel mViewModel;
    public SignupFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        OnCompleteListener<AuthResult> listener = task -> {
            setResult(RESULT_OK);
            overridePendingTransition(0, 0);
            finish();
        };
        mViewModel.init(listener);

        fragment = new SignupFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();

    }

    @Override
    public void onBackPressed() {
        CommonUtils.closeApp(this);
    }
}
