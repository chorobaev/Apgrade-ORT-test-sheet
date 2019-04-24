package com.example.apgrate.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.model.Test;
import com.example.apgrate.screens.test.TestActivity;
import com.example.apgrate.utils.CommonUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVTestsAdapter extends RecyclerView.Adapter<RVTestsAdapter.TestViewHolder> {

    private final String TAG = "MyRVTestAdapter";
    private Context mContext;
    private ArrayList<Test> tests;

    public RVTestsAdapter(Context context) {
        mContext = context;
        tests = new ArrayList<>();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.test_item_layout, parent, false);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.tvTestName.setText(Common.getPureTestName(test.getName()));
        holder.tvLanguage.setText(Common.getTestLanguageFromName(test.getName()));
        if (!(test.getStatus() == Test.TestStatus.OPEN)) {
            holder.ivStatus.setImageResource(R.drawable.lock_outline);
        }
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void updateData(ArrayList<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestName;
        ImageView ivStatus;
        TextView tvLanguage;

        TestViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTestName = itemView.findViewById(R.id.tv_test_name);
            tvLanguage = itemView.findViewById(R.id.tv_language);
            ivStatus = itemView.findViewById(R.id.iv_availability);

            itemView.setClickable(true);

            itemView.setOnClickListener(view -> {
                checkTestAvailability();
            });
        }

        private void checkTestAvailability() {
            if (tests.get(getPosition()).getStatus() != Test.TestStatus.CLOSED) {
                confirmUsersChose();
            } else {
                AlertDialog dialog = new AlertDialog.Builder(mContext).create();
                dialog.setTitle(mContext.getResources().getString(R.string.dialog_title_warning));
                dialog.setMessage(mContext.getResources().getString(R.string.dialog_test_not_available_msg));
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                        mContext.getResources().getString(R.string.dialog_ok_btn),
                        ((dialog1, which) -> dialog.dismiss()));
                dialog.show();
            }
        }

        private void gotoTestActivity() {
            Intent intent  = new Intent(mContext, TestActivity.class);
            intent.putExtra(TestActivity.ACTUAL_TEST, tests.get(getPosition()));
            mContext.startActivity(intent);
        }

        private void confirmUsersChose() {
            DialogInterface.OnClickListener listener = (dialog, which) -> {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        gotoTestActivity();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        // TODO: stay in this activity
                        break;
                }
            };
            String title = mContext.getResources().getString(R.string.dialog_start_test_title);
            String msg = mContext.getResources().getString(R.string.dialog_start_test_msg);
            String positiveBtn = mContext.getResources().getString(R.string.dialog_yes_btn);
            String negativeBtn = mContext.getResources().getString(R.string.dialog_no_btn);

            CommonUtils.showYesNoDialog(mContext, title, msg, positiveBtn, negativeBtn, listener);
        }
    }
}
