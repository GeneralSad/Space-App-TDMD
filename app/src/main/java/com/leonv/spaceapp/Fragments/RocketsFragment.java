package com.leonv.spaceapp.Fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leonv.spaceapp.Viewmodels.RocketsViewModel;
import com.leonv.spaceapp.databinding.RocketsFragmentBinding;

public class RocketsFragment extends Fragment {

    private static final String LOGTAG = RocketsFragment.class.getName();

    private RocketsViewModel mapViewModel;
    private RocketsFragmentBinding binding;

    public static RocketsFragment newInstance() {
        return new RocketsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mapViewModel = new ViewModelProvider(this).get(RocketsViewModel.class);

        binding = RocketsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRockets;
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}