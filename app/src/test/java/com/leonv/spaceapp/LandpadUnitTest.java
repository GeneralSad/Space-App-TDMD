package com.leonv.spaceapp;

import static org.junit.Assert.assertEquals;

import com.leonv.spaceapp.Models.Landpad;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LandpadUnitTest {

    final static String name = "testName";
    final static String fullName = "testFellName";
    final static String status = "testStatus";
    final static String type = "testType";
    final static String locality = "testLocality";
    final static String region = "testRegion";
    final static double latitude = 1.0;
    final static double longitude = 2.0;
    final static int landingAttempts = 1;
    final static int landingSuccesses = 2;
    final static String wikipedia = "testWikipedia";
    final static String details = "testDetails";
    final static ArrayList<String> flightIds = new ArrayList<>();
    final static String id = "testId";

    Landpad landpad;

    @Before
    public void onTestStart()
    {

        flightIds.add("testFlight");

        landpad = new Landpad(name, fullName, status, type, locality, region, latitude, longitude,
                landingAttempts, landingSuccesses, wikipedia, details, flightIds, id);

    }

    @Test
    public void getNameTest() {
        assertEquals(name, landpad.getName());
    }

    @Test
    public void getFullNameTest() {
        assertEquals(fullName, landpad.getFullName());
    }

    @Test
    public void getLocalityTest() {
        assertEquals(locality, landpad.getLocality());
    }

    @Test
    public void getRegionTest() {
        assertEquals(region, landpad.getRegion());
    }

    @Test
    public void getLandingAttemptsTest() {
        assertEquals(landingAttempts, landpad.getLandingAttempts());
    }

    @Test
    public void getLandingSuccessesTest() {
        assertEquals(landingSuccesses, landpad.getLandingSuccesses());
    }

    @Test
    public void getWikipediaTest() {
        assertEquals(wikipedia, landpad.getWikipedia());
    }

    @Test
    public void getStatusTest() {
        assertEquals(status, landpad.getStatus());
    }

    @Test
    public void getTypeTest() {
        assertEquals(type, landpad.getType());
    }

    @Test
    public void getDetailsTest() {
        assertEquals(details, landpad.getDetails());
    }

    @Test
    public void getFlightIdsTest() {
        assertEquals(flightIds, landpad.getFlightIds());
    }

    @Test
    public void getIdTest() {
        assertEquals(id, landpad.getId());
    }

    @Test
    public void getLatitudeTest() {
        assertEquals(latitude, landpad.getLatitude(), 0.000000001);
    }

    @Test
    public void getLongitudeTest() {
        assertEquals(longitude, landpad.getLongitude(), 0.000000001);
    }

}
