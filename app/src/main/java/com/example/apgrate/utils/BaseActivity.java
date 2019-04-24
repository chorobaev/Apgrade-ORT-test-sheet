package com.example.apgrate.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String CONFIG_LANGUAGE = "app_language_config";
    public static final String CURRENT_LANGUAGE = "chosen_language";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences sharedPreferences = getSharedPreferences(CONFIG_LANGUAGE, Context.MODE_PRIVATE);
        String lang = sharedPreferences.getString(CURRENT_LANGUAGE, "ky");

        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lang.toLowerCase())); // API 17+ only.
        } else {
            conf.locale = new Locale(lang.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
}
