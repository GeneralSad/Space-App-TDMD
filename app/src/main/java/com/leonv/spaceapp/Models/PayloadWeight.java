package com.leonv.spaceapp.Models;

import com.leonv.spaceapp.Fragments.MapFragment;

public class PayloadWeight {

    private static final String LOGTAG = PayloadWeight.class.getName();

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
