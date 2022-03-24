package com.leonv.spaceapp.Models;

public class RocketFlightCore {

    private int coreFlightNumber;
    private boolean reusedCore;
    private String landingType;
    private String landpadId;

    public RocketFlightCore(int coreFlightNumber, boolean reusedCore, String landingType, String landpadId) {
        this.coreFlightNumber = coreFlightNumber;
        this.reusedCore = reusedCore;
        this.landingType = landingType;
        this.landpadId = landpadId;
    }

    public int getCoreFlightNumber() {
        return coreFlightNumber;
    }

    public boolean isReusedCore() {
        return reusedCore;
    }

    public String getLandingType() {
        return landingType;
    }

    public String getLandpadId() {
        return landpadId;
    }
}
