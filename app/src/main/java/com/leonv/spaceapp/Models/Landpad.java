package com.leonv.spaceapp.Models;

import java.util.ArrayList;

public class Landpad {

    private String name;
    private String fullName;
    private String status;
    private String type;
    private String locality;
    private String region;
    private double latitude;
    private double longitude;
    private int landingAttempts;
    private int landingSuccesses;
    private String wikipedia;
    private String details;
    private ArrayList<String> flightIds;
    private String id;

    public Landpad(String name, String fullName, String status, String type, String locality, String region, double latitude, double longitude, int landingAttempts, int landingSuccesses, String wikipedia, String details, ArrayList<String> flightIds, String id) {
        this.name = name;
        this.fullName = fullName;
        this.status = status;
        this.type = type;
        this.locality = locality;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.landingAttempts = landingAttempts;
        this.landingSuccesses = landingSuccesses;
        this.wikipedia = wikipedia;
        this.details = details;
        this.flightIds = flightIds;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public int getLandingAttempts() {
        return landingAttempts;
    }

    public int getLandingSuccesses() {
        return landingSuccesses;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getDetails() {
        return details;
    }

    public ArrayList<String> getFlightIds() {
        return flightIds;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
