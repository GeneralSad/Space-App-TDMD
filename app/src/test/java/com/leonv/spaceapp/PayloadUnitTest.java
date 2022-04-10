package com.leonv.spaceapp;

import static org.junit.Assert.assertEquals;

import com.leonv.spaceapp.Models.Payload;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PayloadUnitTest {

    final static String name = "testName";
    final static String type = "testType";
    final static ArrayList<String> customers = new ArrayList<>();
    final static ArrayList<String> nationalities = new ArrayList<>();
    final static ArrayList<String> manufacturers = new ArrayList<>();
    final static int mass = 1;
    final static String orbitType = "testOrbitType";
    final static int lifeSpan = 2;
    final static String id = "testId";

    Payload payload;

    @Before
    public void onTestStart()
    {

        customers.add("testCustomer");

        nationalities.add("testNationality");

        manufacturers.add("testManufacturer");

        payload = new Payload(name, type, customers, nationalities, manufacturers, mass, orbitType,
                lifeSpan, id);

    }

    @Test
    public void getNameTest() {
        assertEquals(name, payload.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals(type, payload.getType());
    }

    @Test
    public void getCustomersTest() {
        assertEquals(customers, payload.getCustomers());
    }

    @Test
    public void getNationalitiesTest() {
        assertEquals(nationalities, payload.getNationalities());
    }

    @Test
    public void getManufacturersTest() {
        assertEquals(manufacturers, payload.getManufacturers());
    }

    @Test
    public void getMassTest() {
        assertEquals(mass, payload.getMass());
    }

    @Test
    public void getOrbitTypeTest() {
        assertEquals(orbitType, payload.getOrbitType());
    }

    @Test
    public void getLifeSpanTest() {
        assertEquals(lifeSpan, payload.getLifeSpan());
    }

    @Test
    public void getIdTest() {
        assertEquals(id, payload.getId());
    }

}
