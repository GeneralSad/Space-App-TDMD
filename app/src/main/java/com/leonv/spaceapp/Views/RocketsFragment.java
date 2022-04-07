package com.leonv.spaceapp.Views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Viewmodels.RocketsViewModel;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;
import com.leonv.spaceapp.databinding.RocketsFragmentBinding;

public class RocketsFragment extends Fragment {

    private static final String LOGTAG = RocketsFragment.class.getName();

    private RocketsViewModel rocketsViewModel;

    public static RocketsFragment newInstance() {
        return new RocketsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.rocketsViewModel = new ViewModelProvider(requireActivity()).get(RocketsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_fragment, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            Context context = view.getContext();

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            RocketsRecyclerViewAdapter itemRecyclerViewAdapter = new RocketsRecyclerViewAdapter(rocketsViewModel.getRockets(), rocketsViewModel);
            recyclerView.setAdapter(itemRecyclerViewAdapter);
        }

        return view;
    }

}