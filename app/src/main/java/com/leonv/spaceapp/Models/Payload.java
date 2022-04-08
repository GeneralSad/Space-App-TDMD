package com.leonv.spaceapp.Models;

import java.util.ArrayList;

public class Payload {

    private static final String LOGTAG = Payload.class.getName();

    private String name;
    private String type;
    private ArrayList<String> customers;
    private ArrayList<String> nationalities;
    private ArrayList<String> manufacturers;
    private int mass;
    private String orbitType;
    private int lifeSpan;
    private String id;

    public Payload(String name, String type, ArrayList<String> customers, ArrayList<String> nationalities, ArrayList<String> manufacturers, int mass, String orbitType, int lifeSpan, String id) {
        this.name = name;
        this.type = type;
        this.customers = customers;
        this.nationalities = nationalities;
        this.manufacturers = manufacturers;
        this.mass = mass;
        this.orbitType = orbitType;
        this.lifeSpan = lifeSpan;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<String> getCustomers() {
        return customers;
    }

    public ArrayList<String> getNationalities() {
        return nationalities;
    }

    public ArrayList<String> getManufacturers() {
        return manufacturers;
    }

    public int getMass() {
        return mass;
    }

    public String getOrbitType() {
        return orbitType;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public String getId() {
        return id;
    }
}
