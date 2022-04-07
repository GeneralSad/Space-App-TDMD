package com.leonv.spaceapp.Views;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;

public class UpcomingFragment extends Fragment {

    private static final String LOGTAG = UpcomingFragment.class.getName();

    private UpcomingViewModel upcomingViewModel;

    public static UpcomingFragment newInstance() {
        return new UpcomingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.upcomingViewModel = new ViewModelProvider(requireActivity()).get(UpcomingViewModel.class);
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