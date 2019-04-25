package com.flaterlab.apgrade.screens.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flaterlab.apgrade.R;
import com.flaterlab.apgrade.utils.BaseFragment;
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroFragment extends BaseFragment implements ISlideBackgroundColorHolder {

    private static final String INTRO_IMAGE = "SlideImage";
    private static final String INTRO_MESSAGE = "SlideMessage";
    private int mImage;
    private int mMessageRes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage = getArguments().getInt(INTRO_IMAGE);
        mMessageRes = getArguments().getInt(INTRO_MESSAGE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.introduction_fragment, container, false);
        ImageView imageView = v.findViewById(R.id.iv_icon);
        imageView.setImageResource(mImage);
        TextView textView = v.findViewById(R.id.tv_message);
        textView.setText(getResources().getText(mMessageRes));

        return v;
    }

    public static Fragment getInstance(@Nullable Integer imageResource, @NonNull int res) {

        Bundle bundle = new Bundle();
        if (imageResource != null) {
            bundle.putInt(IntroFragment.INTRO_IMAGE, imageResource);
        }
        bundle.putInt(IntroFragment.INTRO_MESSAGE, res);
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
