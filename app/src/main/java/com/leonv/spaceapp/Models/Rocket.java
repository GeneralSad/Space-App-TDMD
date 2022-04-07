package com.leonv.spaceapp.Models;

import java.util.ArrayList;

public class Rocket {

    private static final String LOGTAG = Rocket.class.getName();

    private int height;
    private int diameter;
    private int mass;
    private boolean FS_Reusable;
    private int FS_Engines;
    private int FS_FuelInTons;
    private int SS_Engines;
    private int SS_FuelInTons;
    private String engines_Type;
    private int engines_EngineLossMax;
    private String propellant1;
    private String propellant2;
    private double TWR;

    private ArrayList<PayloadWeight> payload_Weights;
    private String name;
    private String type;
    private boolean active;
    private int stages;
    private int boosters;
    private int launchCostDollar;
    private int succesRate;
    private String company;
    private String wikipediaLink;
    private String description;
    private String rocketId;

    public Rocket(int height, int diameter, int mass, boolean FS_Reusable, int FS_Engines, int FS_FuelInTons, int SS_Engines, int SS_FuelInTons, String engines_Type, int engines_EngineLossMax, String propellant1, String propellant2, double TWR, ArrayList<PayloadWeight> payload_Weights, String name, String type, boolean active, int stages, int boosters, int launchCostDollar, int succesRate, String company, String wikipediaLink, String description, String rocketId) {
        this.height = height;
        this.diameter = diameter;
        this.mass = mass;
        this.FS_Reusable = FS_Reusable;
        this.FS_Engines = FS_Engines;
        this.FS_FuelInTons = FS_FuelInTons;
        this.SS_Engines = SS_Engines;
        this.SS_FuelInTons = SS_FuelInTons;
        this.engines_Type = engines_Type;
        this.engines_EngineLossMax = engines_EngineLossMax;
        this.propellant1 = propellant1;
        this.propellant2 = propellant2;
        this.TWR = TWR;
        this.payload_Weights = payload_Weights;
        this.name = name;
        this.type = type;
        this.active = active;
        this.stages = stages;
        this.boosters = boosters;
        this.launchCostDollar = launchCostDollar;
        this.succesRate = succesRate;
        this.company = company;
        this.wikipediaLink = wikipediaLink;
        this.description = description;
        this.rocketId = rocketId;
    }

    public int getHeight() {
        return height;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getMass() {
        return mass;
    }

    public boolean isFS_Reusable() {
        return FS_Reusable;
    }

    public int getFS_Engines() {
        return FS_Engines;
    }

    public int getFS_FuelInTons() {
        return FS_FuelInTons;
    }

    public int getSS_Engines() {
        return SS_Engines;
    }

    public int getSS_FuelInTons() {
        return SS_FuelInTons;
    }

    public String getEngines_Type() {
        return engines_Type;
    }

    public int getEngines_EngineLossMax() {
        return engines_EngineLossMax;
    }

    public String getPropellant1() {
        return propellant1;
    }

    public String getPropellant2() {
        return propellant2;
    }

    public double getTWR() {
        return TWR;
    }

    public ArrayList<PayloadWeight> getPayload_Weights() {
        return payload_Weights;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public int getStages() {
        return stages;
    }

    public int getBoosters() {
        return boosters;
    }

    public int getLaunchCostDollar() {
        return launchCostDollar;
    }

    public int getSuccesRate() {
        return succesRate;
    }

    public String getCompany() {
        return company;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public String getDescription() {
        return description;
    }

    public String getRocketId() {
        return rocketId;
    }
}
