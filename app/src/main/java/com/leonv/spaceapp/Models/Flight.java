package com.leonv.spaceapp.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Flight implements Serializable {

    private boolean hasReusedFairings;
    private String webcastLink;
    private String articleLink;
    private String wikipediaLink;
    private String staticFireDateUtc;
    private boolean isTBD;
    private boolean isNET;
    private String rocketId;
    private String launchDetails;
    private ArrayList<String> payloadIds;
    private String launchpadId;
    private int flightNumber;
    private String name;
    private String launchDateUtc;
    private String datePrecision;
    private ArrayList<RocketFlightCore> cores;
    private String flightId;

    public Flight(boolean hasReusedFairings, String webcastLink, String articleLink, String wikipediaLink, String staticFireDateUtc, boolean isTBD, boolean isNET, String rocketId, String launchDetails, ArrayList<String> payloadIds, String launchpadId, int flightNumber, String name, String launchDateUtc, String datePrecision, ArrayList<RocketFlightCore> cores, String flightId) {
        this.hasReusedFairings = hasReusedFairings;
        this.webcastLink = webcastLink;
        this.articleLink = articleLink;
        this.wikipediaLink = wikipediaLink;
        this.staticFireDateUtc = staticFireDateUtc;
        this.isTBD = isTBD;
        this.isNET = isNET;
        this.rocketId = rocketId;
        this.launchDetails = launchDetails;
        this.payloadIds = payloadIds;
        this.launchpadId = launchpadId;
        this.flightNumber = flightNumber;
        this.name = name;
        this.launchDateUtc = launchDateUtc;
        this.datePrecision = datePrecision;
        this.cores = cores;
        this.flightId = flightId;
    }

    public boolean hasReusedFairings() {
        return hasReusedFairings;
    }

    public String getWebcastLink() {
        return webcastLink;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public String getStaticFireDateUtc() {
        return staticFireDateUtc;
    }

    public boolean isTBD() {
        return isTBD;
    }

    public boolean isNET() {
        return isNET;
    }

    public String getRocketId() {
        return rocketId;
    }

    public String getLaunchDetails() {
        return launchDetails;
    }

    public ArrayList<String> getPayloadIds() {
        return payloadIds;
    }

    public String getLaunchpadId() {
        return launchpadId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getName() {
        return name;
    }

    public String getLaunchDateUtc() {
        return launchDateUtc;
    }

    public String getDatePrecision() {
        return datePrecision;
    }

    public ArrayList<RocketFlightCore> getCores() {
        return cores;
    }

    public String getFlightId() {
        return flightId;
    }
}
