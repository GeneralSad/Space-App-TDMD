package com.leonv.spaceapp.Fragments;

import androidx.lifecycle.MutableLiveData;
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

import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.FlightsRecyclerViewAdapter;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Viewmodels.MapViewModel;
import com.leonv.spaceapp.Viewmodels.RocketsViewModel;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;
import com.leonv.spaceapp.databinding.MapFragmentBinding;
import com.leonv.spaceapp.databinding.RocketsFragmentBinding;
import com.leonv.spaceapp.databinding.UpcomingFragmentBinding;

public class UpcomingFragment extends Fragment {

    private static final String LOGTAG = UpcomingFragment.class.getName();

    private UpcomingViewModel upcomingViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_fragment, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            Context context = view.getContext();

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            FlightsRecyclerViewAdapter itemRecyclerViewAdapter = new FlightsRecyclerViewAdapter(upcomingViewModel.getFlights(), upcomingViewModel);
            recyclerView.setAdapter(itemRecyclerViewAdapter);
        }

        return view;
    }

}