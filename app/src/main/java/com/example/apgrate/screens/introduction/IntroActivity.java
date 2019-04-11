package com.example.apgrate.screens.introduction;

import android.content.Intent;
import android.os.Bundle;

import com.example.apgrate.R;
import com.example.apgrate.utils.CommonUtils;
import com.github.paolorotolo.appintro.AppIntro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_first_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_second_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_third_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_fourth_message)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onBackPressed() {
        CommonUtils.closeApp(this);
    }
}
