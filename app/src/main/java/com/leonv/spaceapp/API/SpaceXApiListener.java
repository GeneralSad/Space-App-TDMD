package com.leonv.spaceapp.API;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.Rocket;

import java.util.ArrayList;
import java.util.List;

//Listener methods for all the different API calls
public interface SpaceXApiListener {
    default void onFlightAvailable(Flight flight) {};
    default void onFlightsAvailable(ArrayList<Flight> flights) {};
    default void onRocketAvailable(Rocket rocket) {};
    default void onRocketsAvailable(ArrayList<Rocket> rockets) {};
    default void onPayloadAvailable(Payload payload) {};
    default void onLaunchpadAvailable(Launchpad launchpad) {};
    default void onLaunchpadsAvailable(List<Launchpad> launchpads) {};
    default void onLandPadAvailable(Landpad landpad) {};
    default void onDataError(Error error) {};
}