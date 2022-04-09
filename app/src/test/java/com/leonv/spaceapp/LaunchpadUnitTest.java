package com.leonv.spaceapp;

import static org.junit.Assert.assertEquals;

import com.leonv.spaceapp.Models.Launchpad;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LaunchpadUnitTest {

    final static String name = "testName";
    final static String fullName = "testFellName";
    final static String locality = "testLocality";
    final static String region = "testRegion";
    final static int launch_attempts = 1;
    final static int launch_successes = 2;
    final static ArrayList<String> rocketIds = new ArrayList<>();
    final static String timezone = "testTimezone";
    final static double latitude = 1.0;
    final static double longitude = 2.0;
    final static ArrayList<String> launchIds = new ArrayList<>();
    final static String status = "testStatus";
    final static String details = "testDetails";
    final static String id = "testId";

    Launchpad launchpad;

    @Before
    public void onTestStart()
    {

        rocketIds.add("testRocket");

        launchIds.add("testLaunch");


        launchpad = new Launchpad(name, fullName, locality, region, launch_attempts,
                launch_successes, rocketIds, timezone, latitude, longitude, launchIds, status,
                details, id);

    }

    @Test
    public void getNameTest() {
        assertEquals(name, launchpad.getName());
    }

    @Test
    public void getFullNameTest() {
        assertEquals(fullName, launchpad.getFullName());
    }

    @Test
    public void getLocalityTest() {
        assertEquals(locality, launchpad.getLocality());
    }

    @Test
    public void getRegionTest() {
        assertEquals(region, launchpad.getRegion());
    }

    @Test
    public void getLaunch_attemptsTest() {
        assertEquals(launch_attempts, launchpad.getLaunch_attempts());
    }

    @Test
    public void getLaunch_successesTest() {
        assertEquals(launch_successes, launchpad.getLaunch_successes());
    }

    @Test
    public void getRocketIdsTest() {
        assertEquals(rocketIds, launchpad.getRocketIds());
    }

    @Test
    public void getTimezoneTest() {
        assertEquals(timezone, launchpad.getTimezone());
    }

    @Test
    public void getLaunchIdsTest() {
        assertEquals(launchIds, launchpad.getLaunchIds());
    }

    @Test
    public void getStatusTest() {
        assertEquals(status, launchpad.getStatus());
    }

    @Test
    public void getDetailsTest() {
        assertEquals(details, launchpad.getDetails());
    }

    @Test
    public void getIdTest() {
        assertEquals(id, launchpad.getId());
    }

    @Test
    public void getLatitudeTest() {
        assertEquals(latitude, launchpad.getLatitude(), 0.000000001);
    }

    @Test
    public void getLongitudeTest() {
        assertEquals(longitude, launchpad.getLongitude(), 0.000000001);
    }

}
