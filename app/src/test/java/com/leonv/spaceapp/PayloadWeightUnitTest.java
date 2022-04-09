package com.leonv.spaceapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.leonv.spaceapp.Models.PayloadWeight;
import com.leonv.spaceapp.Models.Rocket;

import org.junit.Before;
import org.junit.Test;

public class PayloadWeightUnitTest {

    final static String id = "testId";
    final static String name = "testName";
    final static int mass = 1;

    PayloadWeight payloadWeight;

    @Before
    public void onTestStart()
    {

        payloadWeight = new PayloadWeight(id, name, mass);

    }

    @Test
    public void getNameTest() {
        assertEquals(name, payloadWeight.getName());
    }

    @Test
    public void getIdTest() {
        assertEquals(id, payloadWeight.getId());
    }

    @Test
    public void getMassTest() {
        assertEquals(mass, payloadWeight.getMass());
    }

}
