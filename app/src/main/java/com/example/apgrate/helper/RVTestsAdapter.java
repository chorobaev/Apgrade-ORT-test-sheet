package com.example.apgrate.helper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apgrate.R;
import com.example.apgrate.model.Test;

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
        holder.tvTestName.setText(test.getName());
        holder.tvTestStatus.setText(test.getStatus().toString());
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
        TextView tvTestStatus;

        TestViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTestName = itemView.findViewById(R.id.tv_test_name);
            tvTestStatus = itemView.findViewById(R.id.tv_test_status);

            itemView.setOnClickListener(view -> {
                Log.d(TAG, "Item clicked " + getPosition());
                gotoTestActivity();
            });
        }

        private void gotoTestActivity() {
            // TODO: implement goto TestActivity
        }
    }
}
