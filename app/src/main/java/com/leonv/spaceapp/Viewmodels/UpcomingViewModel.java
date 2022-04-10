package com.leonv.spaceapp.Viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leonv.spaceapp.API.SpaceXApiListener;
import com.leonv.spaceapp.API.SpaceXApiManager;
import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Rocket;
import com.leonv.spaceapp.utils.OnItemClickListener;
import com.leonv.spaceapp.utils.SpaceApp;

import java.util.ArrayList;
import java.util.List;

public class UpcomingViewModel extends AndroidViewModel implements SpaceXApiListener, OnItemClickListener {

    private static final String LOGTAG = UpcomingViewModel.class.getName();

    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Rocket> rockets = new ArrayList<>();
    private final MutableLiveData<Flight> selectedFlight = new MutableLiveData<>();
    private final SpaceXApiManager spaceXApiManager;
    private final ArrayList<UpcomingViewModel.FlightsListener> flightsListeners = new ArrayList<>();

    public UpcomingViewModel(@NonNull Application application) {
        super(application);
        this.spaceXApiManager = ((SpaceApp)application).getApiManager();
        this.spaceXApiManager.addListener(this);
        //this.spaceXApiManager.getFlightsData("upcoming");
    }

    public ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void requestFlights() {
        spaceXApiManager.getFlightsData("upcoming");
    }

    public void requestRockets() {
        spaceXApiManager.getRocketsData();
    }

    @Override
    public void onFlightsAvailable(ArrayList<Flight> flights) {
        this.flights = flights;
        this.flightsListeners.forEach(x -> x.onFlightsAvailable(this.flights));
    }

    @Override
    public void onRocketsAvailable(ArrayList<Rocket> rockets) {
        this.rockets =rockets;
        this.flightsListeners.forEach(x -> x.onRocketsAvailable(this.rockets));
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "Pressed: " + flights.get(clickedPosition).getName());
        this.selectedFlight.setValue(flights.get(clickedPosition));
    }

    public interface FlightsListener {
        void onFlightsAvailable(List<Flight> flightList);
        default void onRocketsAvailable(List<Rocket> rocketsList) {};
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