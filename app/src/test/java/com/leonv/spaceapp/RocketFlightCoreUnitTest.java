package com.leonv.spaceapp;

import static org.junit.Assert.assertEquals;

import com.leonv.spaceapp.Models.PayloadWeight;
import com.leonv.spaceapp.Models.RocketFlightCore;

import org.junit.Before;
import org.junit.Test;

public class RocketFlightCoreUnitTest {

    final static int coreFlightNumber = 1;
    final static boolean reusedCore = true;
    final static String landingType = "testLandingType";
    final static String landpadId = "testLandpadId";

    RocketFlightCore rocketFlightCore;

    @Before
    public void onTestStart()
    {

        rocketFlightCore = new RocketFlightCore(coreFlightNumber, reusedCore, landingType, landpadId);

    }

    @Test
    public void getCoreFlightNumberTest() {
        assertEquals(coreFlightNumber, rocketFlightCore.getCoreFlightNumber());
    }

    @Test
    public void isReusedCoreTest() {
        assertEquals(reusedCore, rocketFlightCore.isReusedCore());
    }

    @Test
    public void getLandingTypeTest() {
        assertEquals(landingType, rocketFlightCore.getLandingType());
    }

    @Test
    public void getLandpadIdTest() {
        assertEquals(landpadId, rocketFlightCore.getLandpadId());
    }
}
