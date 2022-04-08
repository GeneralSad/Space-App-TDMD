package com.leonv.spaceapp.Viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Fragments.MapFragment;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.OnItemClickListener;
import com.leonv.spaceapp.SpaceApp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UpcomingViewModel extends AndroidViewModel implements SpaceXApiListener, OnItemClickListener {

    private static final String LOGTAG = UpcomingViewModel.class.getName();

    private final ArrayList<Flight> flights = new ArrayList<>();
    private final MutableLiveData<Flight> selectedFlight = new MutableLiveData<>();
    private final SpaceXApiManager spaceXApiManager;
    private final ArrayList<UpcomingViewModel.FlightsListener> flightsListeners = new ArrayList<>();

    public UpcomingViewModel(@NonNull Application application) {
        super(application);
        this.spaceXApiManager = ((SpaceApp)application).getApiManager();
        this.spaceXApiManager.addListener(this);
        this.spaceXApiManager.getFlightsData("upcoming");
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public void onFlightAvailable(Flight flight) {
        if(this.flights.contains(flight))
            return;

        this.flights.add(flight);
        this.flightsListeners.forEach(x -> x.onFlightsAvailable(this.flights));
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "Pressed: " + flights.get(clickedPosition).getName());
        this.selectedFlight.setValue(flights.get(clickedPosition));
    }

    public interface FlightsListener {
        void onFlightsAvailable(List<Flight> flightList);
    }

    public void addFlightsListener(UpcomingViewModel.FlightsListener flightsListener)
    {
        if(this.flightsListeners.contains(flightsListener))
            return;

        this.flightsListeners.add(flightsListener);
    }

    public void removeFlightsListener(UpcomingViewModel.FlightsListener flightsListener)
    {
        if(!this.flightsListeners.contains(flightsListener))
            return;

        this.flightsListeners.remove(flightsListener);
    }

}