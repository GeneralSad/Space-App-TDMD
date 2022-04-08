package com.leonv.spaceapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.leonv.spaceapp.Models.Flight;
import com.leonv.spaceapp.Models.RocketFlightCore;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class FlightUnitTest {

    final static boolean hasReusedFairings = true;
    final static String webcastLink = "Webcast";
    final static String articleLink = "Article";
    final static String wikipediaLink = "Wikipedia";
    final static String staticFireDateUtc = "2022-01-01T07:00:00.000Z";
    final static boolean isTBD = false;
    final static boolean isNET = false;
    final static String rocketId = "testRocketId";
    final static String launchDetails = "testDetails";
    final static ArrayList<String> payloadIds = new ArrayList<>();
    final static String launchpadId = "testLaunchpadId";
    final static int flightNumber = 1;
    final static String name = "testFlight";
    final static String launchDateUtc = "2022-01-01T12:00:00.000Z";
    final static String datePrecision = "hour";
    final static ArrayList<RocketFlightCore> cores = new ArrayList<>();
    final static String flightId = "testFlightId";
    final static String missionPatch = "MissionPatch";

    Flight flight;

    @Before
    public void onTestStart()
    {
        payloadIds.add("testPayload");

        cores.add(mock(RocketFlightCore.class));

        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, datePrecision, cores, flightId, missionPatch);

    }

    @Test(expected = ParseException.class)
    public void constructorTest() {
        Flight testFlight = Mockito.spy(Flight.class);
        new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, "launchDateUtc", datePrecision, cores, flightId, missionPatch);
    }

    @Test
    public void hasReusedFairingsTest() {
        assertEquals(hasReusedFairings, flight.hasReusedFairings());
    }

    @Test
    public void getWebcastLinkTest() {
        assertEquals(webcastLink, flight.getWebcastLink());
    }

    @Test
    public void getArticleLinkTest() {
        assertEquals(articleLink, flight.getArticleLink());
    }

    @Test
    public void getWikipediaLinkTest() {
        assertEquals(wikipediaLink, flight.getWikipediaLink());
    }

    @Test
    public void isTBDTest() {
        assertEquals(isTBD, flight.isTBD());
    }

    @Test
    public void isNETTest() {
        assertEquals(isNET, flight.isNET());
    }

    @Test
    public void getRocketIdTest() {
        assertEquals(rocketId, flight.getRocketId());
    }

    @Test
    public void getLaunchDetailsTest() {
        assertEquals(launchDetails, flight.getLaunchDetails());
    }

    @Test
    public void getPayloadIdsTest() {
        assertEquals(payloadIds, flight.getPayloadIds());
    }

    @Test
    public void getLaunchpadIdTest() {
        assertEquals(launchpadId, flight.getLaunchpadId());
    }

    @Test
    public void getFlightNumberTest() {
        assertEquals(flightNumber, flight.getFlightNumber());
    }

    @Test
    public void getNameTest() {
        assertEquals(name, flight.getName());
    }

    @Test
    public void getDatePrecisionTest() {
        assertEquals(datePrecision, flight.getDatePrecision());
    }

    @Test
    public void getCoresTest() {
        assertEquals(cores, flight.getCores());
    }

    @Test
    public void getFlightIdTest() {
        assertEquals(flightId, flight.getFlightId());
    }

    @Test
    public void getMissionPatchTest() {
        assertEquals(missionPatch, flight.getMissionPatch());
    }

    @Test
    public void getStaticFireDateTest() {
        final String staticfiredate = "1 January 2022";

        assertEquals(staticfiredate, flight.getStaticFireDate());
    }

    @Test
    public void getStaticFireDateTBDTest() {
        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, "", isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, datePrecision, cores, flightId, missionPatch);

        assertEquals("TBD", flight.getStaticFireDate());
    }

    @Test
    public void getLaunchDateTest() {
        final String launchdate = "1 January 2022 13:00";

        assertEquals(launchdate, flight.getLaunchDate());
    }

    @Test
    public void getLaunchDateTBDTest() {
        final String launchdate = "TBD";
        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, "", datePrecision, cores, flightId, missionPatch);

        assertEquals(launchdate, flight.getLaunchDate());
    }

    @Test
    public void getLaunchDateYearTest() {
        final String launchdate = "2022";
        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, "year", cores, flightId, missionPatch);

        assertEquals(launchdate, flight.getLaunchDate());
    }

    @Test
    public void getLaunchDateMonthTest() {
        final String launchdate = "January 2022";
        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, isNET, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, "month", cores, flightId, missionPatch);

        assertEquals(launchdate, flight.getLaunchDate());
    }

    @Test
    public void getLaunchDateNETTest() {
        final String launchdate = "NET 1 January 2022 13:00";
        flight = new Flight(hasReusedFairings, webcastLink, articleLink, wikipediaLink, staticFireDateUtc, isTBD, true, rocketId,
                launchDetails, payloadIds, launchpadId, flightNumber, name, launchDateUtc, datePrecision, cores, flightId, missionPatch);

        assertEquals(launchdate, flight.getLaunchDate());
    }

}