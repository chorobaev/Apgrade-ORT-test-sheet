package com.example.apgrate.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apgrate.R;
import com.example.apgrate.helper.RVRatingsAdapter;
import com.example.apgrate.model.TestResult;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RatingsFragment extends Fragment{

    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private RVRatingsAdapter mAdapter;
    private ArrayList<TestResult> mResults;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);

        mRecyclerView = view.findViewById(R.id.rv_tests);
        mAdapter = new RVRatingsAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        setObservers();

        return view;
    }

    private void setObservers() {
        mViewModel.getRatings().observe(this, dataSnapshot -> {
            mResults = new ArrayList<>();

            for (DataSnapshot d : dataSnapshot.getChildren()) {
                TestResult result = d.getValue(TestResult.class);
                mResults.add(0, result);
            }

            mAdapter.updateData(mResults);
        });
    }
}
