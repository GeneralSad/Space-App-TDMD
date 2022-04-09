package com.leonv.spaceapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.leonv.spaceapp.Models.PayloadWeight;
import com.leonv.spaceapp.Models.Rocket;

import java.util.ArrayList;

public class RocketUnitTest {

    final static int height = 1;
    final static int diameter = 2;
    final static int mass = 3;
    final static boolean FS_Reusable = true;
    final static int FS_Engines = 4;
    final static int FS_FuelInTons = 5;
    final static int SS_Engines = 6;
    final static int SS_FuelInTons = 7;
    final static String engines_Type = "testType";
    final static int engines_EngineLossMax = 8;
    final static String propellant1 = "testProp1";
    final static String propellant2 = "testProp2";
    final static double TWR = 9;

    final static ArrayList<PayloadWeight> payload_Weights = new ArrayList<>();
    final static String name = "testName";
    final static String type = "testType";
    final static boolean active = true;
    final static int stages = 10;
    final static int boosters = 11;
    final static int launchCostDollar = 12;
    final static int successRate = 13;
    final static String company = "testCompany";
    final static String wikipediaLink = "testWikipedia";
    final static String description = "testDescription";
    final static String rocketId = "testRocket";
    final static String image = "testImage";

    Rocket rocket;

    @Before
    public void onTestStart()
    {
        payload_Weights.add(mock(PayloadWeight.class));

        rocket = new Rocket(height, diameter, mass, FS_Reusable, FS_Engines, FS_FuelInTons, SS_Engines,
                SS_FuelInTons, engines_Type, engines_EngineLossMax, propellant1, propellant2, TWR, payload_Weights,
                name, type, active, stages, boosters, launchCostDollar, successRate, company, wikipediaLink, description,
                rocketId, image);

    }

    @Test
    public void getHeightTest() {
        assertEquals(height, rocket.getHeight());
    }

    @Test
    public void getDiameterTest() {
        assertEquals(diameter, rocket.getDiameter());
    }

    @Test
    public void getMassTest() {
        assertEquals(mass, rocket.getMass());
    }

    @Test
    public void isFS_ReusableTest() {
        assertEquals(FS_Reusable, rocket.isFS_Reusable());
    }

    @Test
    public void getFS_EnginesTest() {
        assertEquals(FS_Engines, rocket.getFS_Engines());
    }

    @Test
    public void getFS_FuelInTonsTest() {
        assertEquals(FS_FuelInTons, rocket.getFS_FuelInTons());
    }

    @Test
    public void getSS_EnginesTest() {
        assertEquals(SS_Engines, rocket.getSS_Engines());
    }

    @Test
    public void getSS_FuelInTonsTest() {
        assertEquals(SS_FuelInTons, rocket.getSS_FuelInTons());
    }

    @Test
    public void getEngines_TypeTest() {
        assertEquals(engines_Type, rocket.getEngines_Type());
    }

    @Test
    public void getEngines_EngineLossMaxTest() {
        assertEquals(engines_EngineLossMax, rocket.getEngines_EngineLossMax());
    }

    @Test
    public void getPropellant1Test() {
        assertEquals(propellant1, rocket.getPropellant1());
    }

    @Test
    public void getPropellant2Test() {
        assertEquals(propellant2, rocket.getPropellant2());
    }

    @Test
    public void getTWRTest() {
        assertEquals(TWR, rocket.getTWR(), 0.00000001);
    }

    @Test
    public void getPayload_WeightsTest() {
        assertEquals(payload_Weights, rocket.getPayload_Weights());
    }

    @Test
    public void getNameTest() {
        assertEquals(name, rocket.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals(type, rocket.getType());
    }

    @Test
    public void isActiveTest() {
        assertEquals(active, rocket.isActive());
    }

    @Test
    public void getStagesTest() {
        assertEquals(stages, rocket.getStages());
    }

    @Test
    public void getBoostersTest() {
        assertEquals(boosters, rocket.getBoosters());
    }

    @Test
    public void getLaunchCostDollarTest() {
        assertEquals(launchCostDollar, rocket.getLaunchCostDollar());
    }

    @Test
    public void getSuccessRateTest() {
        assertEquals(successRate, rocket.getSuccessRate());
    }

    @Test
    public void getCompanyTest() {
        assertEquals(company, rocket.getCompany());
    }

    @Test
    public void getWikipediaLinkTest() {
        assertEquals(wikipediaLink, rocket.getWikipediaLink());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(description, rocket.getDescription());
    }

    @Test
    public void getRocketIdTest() {
        assertEquals(rocketId, rocket.getRocketId());
    }

    @Test
    public void getImageTest() {
        assertEquals(image, rocket.getImage());
    }

}
