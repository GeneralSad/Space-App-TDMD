package com.leonv.spaceapp.Viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.OnItemClickListener;

import java.util.ArrayList;

public class UpcomingViewModel extends ViewModel implements SpaceXApiListener, OnItemClickListener {

    private static final String LOGTAG = UpcomingViewModel.class.getName();

    private ArrayList<Flight> flights = new ArrayList<>();
    private MutableLiveData<Flight> selectedFlight = new MutableLiveData<>();
    private SpaceXApiManager spaceXApiManager;

//    public UpcomingViewModel() {
//    }

    public UpcomingViewModel(SpaceXApiManager spaceXApiManager) {
        this.spaceXApiManager = spaceXApiManager;
        this.spaceXApiManager.addListener(this);
        this.spaceXApiManager.getFlightsData("upcoming");
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public void onFlightAvailable(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "Pressed: " + flights.get(clickedPosition).getName());
        this.selectedFlight.setValue(flights.get(clickedPosition));
    }
}