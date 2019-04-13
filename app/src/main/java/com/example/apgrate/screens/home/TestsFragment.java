package com.example.apgrate.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apgrate.R;
import com.example.apgrate.helper.RVTestsAdapter;
import com.example.apgrate.screens.test.TestActivity;
import com.example.apgrate.utils.Tester;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TestsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RVTestsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tests, container, false);

        mRecyclerView = v.findViewById(R.id.rv_tests);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RVTestsAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.updateData(Tester.getTests());

        Intent intent = new Intent(getContext(), TestActivity.class);
        getActivity().startActivity(intent);

        return v;
    }
}