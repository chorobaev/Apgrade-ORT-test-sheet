package com.flaterlab.apgrade.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flaterlab.apgrade.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TestLayout extends LinearLayout{

    private int mTestSize;
    private int[] mAnswers;
    private Context mActivity;
    private LinearLayout mLinearLayout;
    private RadioButton[][] buttons;
    private OnAnswerClickListener mListener;

    public ArrayList<Integer> getAnswerSet() {
        return intToArrayList(mAnswers);
    }

    public void setInitialState(ArrayList<Integer> stateArray) {
        mAnswers = arrayListToInt(stateArray);
        updateButtons();
    }

    public void reset() {
        mAnswers = arrayListToInt(new ArrayList<>());
        updateButtons();
    }

    public void setSize(int testSize) {
        createView(mActivity, testSize);
    }

    public void setOnAnswerClickListener(OnAnswerClickListener listener) {
        mListener = listener;
    }

    private void updateButtons() {
        for (int i = 0; i < mTestSize; i++) {
            for (int j = 1; j < 6; j++) {
                buttons[i][j].setChecked(false);
            }
            if (mAnswers[i] != -1) {
                buttons[i][mAnswers[i]].setChecked(true);
            }
        }
    }

    public TestLayout(Context context) {
        super(context);
    }

    public TestLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        applyAttributes(context, attributeSet);
    }

    public TestLayout(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        applyAttributes(context, attributeSet);
    }

    private void applyAttributes(@NotNull Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TestLayout, 0, 0);
        int testSize = 0;
        try {
            testSize = ta.getInt(R.styleable.TestLayout_size, 0);
            createView(context, testSize);
        } finally {
            ta.recycle();
        }
    }

    private void createView(Context context, int testSize) {
        mActivity = context;
        mTestSize = testSize;
        mAnswers = arrayListToInt(new ArrayList<>());
        buttons = new RadioButton[mTestSize][6];
        initUI();
    }

    private void initUI() {
        setOrientation(VERTICAL);
        removeAllViews();

        createVariantsTitleAndAdd();
        createAnswerSheet();
        addScrollingAnswerSheet();
    }

    private void createVariantsTitleAndAdd() {
        TextView textView = new TextView(mActivity);
        textView.setText(mActivity.getResources().getText(R.string.test_header_variants));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView.setTextColor(mActivity.getResources().getColor(R.color.colorBlack));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = dpToPx(24);
        params.leftMargin = dpToPx(52);
        params.bottomMargin = dpToPx(8);
        textView.setLayoutParams(params);

        addView(textView);
    }

    private void addScrollingAnswerSheet() {
        ScrollView scrollView = new ScrollView(mActivity);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llParams.weight = 1;
        scrollView.setLayoutParams(llParams);
        scrollView.addView(mLinearLayout);

        addView(scrollView);
    }

    private void createAnswerSheet() {
        mLinearLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLinearLayout.setLayoutParams(params);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < mTestSize; i++) {
            createAnswerChooser(i);
        }
    }

    private void createAnswerChooser(int index) {
        LinearLayout linearLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = dpToPx(8);
        params.bottomMargin = dpToPx(8);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setId(index);

        createNumerationAndAdd(linearLayout);
        createRadioGroupAndAdd(linearLayout);

        mLinearLayout.addView(linearLayout);
    }

    private void createNumerationAndAdd(@NotNull ViewGroup parent) {
        TextView orderText = new TextView(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(32), ViewGroup.LayoutParams.WRAP_CONTENT);
        orderText.setLayoutParams(params);
        orderText.setText(String.valueOf(((int) parent.getId() + 1) + "."));
        orderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        orderText.setTextColor(mActivity.getResources().getColor(R.color.colorBlack));
        parent.addView(orderText);
    }

    private void createRadioGroupAndAdd(@NotNull ViewGroup parent) {
        RadioGroup radioGroup = new RadioGroup(mActivity);
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        radioGroup.setLayoutParams(params);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        radioGroup.setGravity(Gravity.BOTTOM);
        radioGroup.setId((parent.getId()+(int)100));

        createRadioButtonsAndAdd(radioGroup);

        parent.addView(radioGroup);
    }

    private void createRadioButtonsAndAdd(ViewGroup group) {
        for (int i = 1; i < 6; i++) {
            RadioButton radioButton = new RadioButton(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = dpToPx(2);
            radioButton.setLayoutParams(params);
            radioButton.setId(i);
            radioButton.setOnClickListener(v -> onAnswerChosen(v, group.getId() - (int)100));
            group.addView(radioButton);

            buttons[group.getId() - (int)100][i] = radioButton;
        }
    }

    private void onAnswerChosen(@NotNull View view, int index) {
        mAnswers[index] = view.getId();
        if (mListener != null) {
            mListener.onAnswerChosen(view, index);
        }
    }

    private int dpToPx(int density) {
        final float scale = mActivity.getResources().getDisplayMetrics().density;
        return (int) (density * scale + 0.5f);
    }

    private ArrayList<Integer> intToArrayList(@NotNull int... a) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : a) {
            list.add(i);
        }

        return list;
    }

    private int[] arrayListToInt(ArrayList<Integer> chose) {
        int[] nums = new int[mTestSize];
        for (int i = 0; i < mTestSize; i++) {
            if (chose.size() > i) {
                nums[i] = chose.get(i);
            } else {
                nums[i] = -1;
            }
        }

        return nums;
    }

    public interface OnAnswerClickListener {
        void onAnswerChosen(View view, int questionIndex);
    }
}
