package com.leonv.spaceapp.Models;

public class PayloadWeight {

    private String id;
    private String name;
    private int mass;

    public PayloadWeight(String id, String name, int mass) {
        this.id = id;
        this.name = name;
        this.mass = mass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMass() {
        return mass;
    }
}
