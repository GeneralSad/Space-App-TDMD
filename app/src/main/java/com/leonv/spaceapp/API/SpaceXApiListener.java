package com.leonv.spaceapp.API;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.Landpad;
import com.leonv.spaceapp.Models.Launchpad;
import com.leonv.spaceapp.Models.Payload;
import com.leonv.spaceapp.Models.Rocket;

public interface SpaceXApiListener {
    public void onFlightAvailable(Flight flight);
    public void onRocketAvailable(Rocket rocket);
    public void onPayloadAvailable(Payload payload);
    public void onLaunchpadAvailable(Launchpad launchpad);
    public void onLandpadAvailable(Landpad landpad);
    public void onDataError(Error error);
}