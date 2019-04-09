package com.example.apgrate.screens.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.utils.BaseFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroFragment extends BaseFragment implements ISlideBackgroundColorHolder {

    private static final String INTRO_IMAGE = "SlideImage";
    private static final String INTRO_MESSAGE = "SlideMessage";
    private int mImage;
    private String mMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage = getArguments().getInt(INTRO_IMAGE);
        mMessage = getArguments().getString(INTRO_MESSAGE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.introduction_fragment, container, false);
        ImageView imageView = v.findViewById(R.id.iv_icon);
        imageView.setImageResource(mImage);
        TextView textView = v.findViewById(R.id.tv_message);
        textView.setText(mMessage);

        return v;
    }

    public static Fragment getInstance(@Nullable Integer imageResource, @NonNull String message) {

        Bundle bundle = new Bundle();
        if (imageResource != null) {
            bundle.putInt(IntroFragment.INTRO_IMAGE, imageResource);
        }
        bundle.putString(IntroFragment.INTRO_MESSAGE, message);
        Fragment fragment = new IntroFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getDefaultBackgroundColor() {
        return getResources().getColor(R.color.colorAppMain);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {

    }
}
