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
import android.widget.LinearLayout;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.R;
import com.leonv.spaceapp.Viewmodels.UpcomingViewModel;

import java.util.List;

public class UpcomingFragment extends Fragment implements UpcomingViewModel.FlightsListener {

    private static final String LOGTAG = UpcomingFragment.class.getName();

    private UpcomingViewModel upcomingViewModel;
    private FlightsRecyclerViewAdapter flightsRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        this.upcomingViewModel.addFlightsListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        upcomingViewModel.requestFlights();
        upcomingViewModel.requestRockets();

        View view = inflater.inflate(R.layout.upcoming_fragment, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            Context context = view.getContext();

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            flightsRecyclerViewAdapter = new FlightsRecyclerViewAdapter(getContext(), upcomingViewModel.getFlights(), upcomingViewModel.getRockets() , upcomingViewModel);
            recyclerView.setAdapter(flightsRecyclerViewAdapter);
        }

        return view;
    }

    @Override
    public void onFlightsAvailable(List<Flight> flightList) {
        flightsRecyclerViewAdapter.updateFlights(flightList);
    }

    @Override
    public void onRocketsAvailable(List<Rocket> rocketsList) {
        flightsRecyclerViewAdapter.updateRockets(rocketsList);
    }
}