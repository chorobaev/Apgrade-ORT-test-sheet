package com.example.apgrate.screens.introduction;

import android.content.Intent;
import android.os.Bundle;

import com.example.apgrate.R;
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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
