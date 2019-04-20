package com.example.apgrate.screens.introduction;

import android.os.Bundle;

import com.example.apgrate.R;
import com.example.apgrate.utils.CommonUtils;
import com.github.paolorotolo.appintro.AppIntro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroActivity extends AppIntro {

    private static boolean isFirstCreate = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSkipText(getResources().getString(R.string.intro_btn_skip));
        setDoneText(getResources().getString(R.string.intro_btn_done));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_first_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_second_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_third_message)));
        addSlide(IntroFragment.getInstance(R.drawable.apgrade_white, getResources().getString(R.string.intro_fourth_message)));

        if (!isFirstCreate) {
            CommonUtils.showChooseLanguageDialog(this);
            isFirstCreate = true;
        }
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        closeActivity();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        closeActivity();
    }

    @Override
    public void onBackPressed() {
        CommonUtils.closeApp(this);
    }

    private void closeActivity() {
        overridePendingTransition(0, 0);
        finish();
    }
}
