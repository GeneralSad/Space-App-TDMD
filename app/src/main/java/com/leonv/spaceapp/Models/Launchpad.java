package com.leonv.spaceapp.Models;

import java.util.ArrayList;

public class Launchpad {

    private String name;
    private String fullName;
    private String locality;
    private String region;
    private int launch_attempts;
    private int launch_successes;
    private ArrayList<String> rocketIds;
    private String timezone;
    private double latitude;
    private double longitude;
    private ArrayList<String> launchIds;
    private String status;
    private String details;
    private String id;

    public Launchpad(String name, String fullName, String locality, String region, int launch_attempts, int launch_successes, ArrayList<String> rocketIds, String timezone, double latitude, double longitude, ArrayList<String> launchIds, String status, String details, String id) {
        this.name = name;
        this.fullName = fullName;
        this.locality = locality;
        this.region = region;
        this.launch_attempts = launch_attempts;
        this.launch_successes = launch_successes;
        this.rocketIds = rocketIds;
        this.timezone = timezone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.launchIds = launchIds;
        this.status = status;
        this.details = details;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public int getLaunch_attempts() {
        return launch_attempts;
    }

    public int getLaunch_successes() {
        return launch_successes;
    }

    public ArrayList<String> getRocketIds() {
        return rocketIds;
    }

    public String getTimezone() {
        return timezone;
    }

    public ArrayList<String> getLaunchIds() {
        return launchIds;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
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