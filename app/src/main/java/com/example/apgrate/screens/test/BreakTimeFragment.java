package com.example.apgrate.screens.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apgrate.R;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BreakTimeFragment extends Fragment {

    private String[] hints;
    private String hint;
    private TextView tvHint;

    public static BreakTimeFragment getInstance() {
        return new BreakTimeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_break_time, container, false);

        tvHint = v.findViewById(R.id.tv_break_time_hint);
        tvHint.setText(hint);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hints = getResources().getStringArray(R.array.test_break_hints);
        hint = hints[new Random().nextInt(hints.length)];
    }
}
