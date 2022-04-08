package com.leonv.spaceapp.Models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Flight implements Serializable {

    private static final String LOGTAG = Flight.class.getName();

    private boolean hasReusedFairings;
    private String webcastLink;
    private String articleLink;
    private String wikipediaLink;
    private Date staticFireDateUtc;
    private boolean isTBD;
    private boolean isNET;
    private String rocketId;
    private String launchDetails;
    private ArrayList<String> payloadIds;
    private String launchpadId;
    private int flightNumber;
    private String name;
    private Date launchDateUtc;
    private String datePrecision;
    private ArrayList<RocketFlightCore> cores;
    private String flightId;
    private String missionPatch;

    private SimpleDateFormat dateFormat;

    public Flight(boolean hasReusedFairings, String webcastLink, String articleLink, String wikipediaLink, String staticFireDateUtc, boolean isTBD, boolean isNET, String rocketId, String launchDetails, ArrayList<String> payloadIds, String launchpadId, int flightNumber, String name, String launchDateUtc, String datePrecision, ArrayList<RocketFlightCore> cores, String flightId, String missionPatch) {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        this.hasReusedFairings = hasReusedFairings;
        this.webcastLink = webcastLink;
        this.articleLink = articleLink;
        this.wikipediaLink = wikipediaLink;
        this.isTBD = isTBD;
        this.isNET = isNET;
        this.rocketId = rocketId;
        this.launchDetails = launchDetails;
        this.payloadIds = payloadIds;
        this.launchpadId = launchpadId;
        this.flightNumber = flightNumber;
        this.name = name;

        try {
            if (!staticFireDateUtc.equals("N/A")) {
                this.staticFireDateUtc = dateFormat.parse(staticFireDateUtc);
            }
            if (!launchDateUtc.equals("N/A")) {
                this.launchDateUtc = dateFormat.parse(launchDateUtc);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.datePrecision = datePrecision;
        this.cores = cores;
        this.flightId = flightId;
        this.missionPatch = missionPatch;
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

    public String getDatePrecision() {
        return datePrecision;
    }

    public ArrayList<RocketFlightCore> getCores() {
        return cores;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getMissionPatch() {
        return missionPatch;
    }

    public String getStaticFireDate() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("d MMMM yyyy");
        yearFormat.setTimeZone(TimeZone.getDefault());
        return yearFormat.format(staticFireDateUtc);
    }

    public String getLaunchDate() {
        if (launchDateUtc == null || isTBD) {
            return "TBD";
        } else if (isNET) {
            if (datePrecision.equals("half") || datePrecision.equals("quarter") | datePrecision.equals("year")) {
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                yearFormat.setTimeZone(TimeZone.getDefault());
                return "NET" + yearFormat.format(launchDateUtc);
            } else if (datePrecision.equals("month")) {
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy");
                monthFormat.setTimeZone(TimeZone.getDefault());
                return "NET" + monthFormat.format(launchDateUtc);
            } else {
                SimpleDateFormat dayFormat = new SimpleDateFormat("d MMMM yyyy");
                dayFormat.setTimeZone(TimeZone.getDefault());
                return "NET" + dayFormat.format(launchDateUtc);
            }
        } else {
            SimpleDateFormat fullFormat = new SimpleDateFormat("d MMMM yyyy");
            fullFormat.setTimeZone(TimeZone.getDefault());
            return fullFormat.format(launchDateUtc);
        }
    }

    private String getLaunchTime() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("HH:mm");
        yearFormat.setTimeZone(TimeZone.getDefault());
        return yearFormat.format(launchDateUtc);
    }

}
