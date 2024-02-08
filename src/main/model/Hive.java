package model;

import java.util.ArrayList;

public class Hive {
    private String name;
    private String location;
    // Color !!!
    private String primaryPollenSource;
    private String secondaryPollenSource;
    private String notes;

    // EFFECT: Constructs a hive with a specified name and location
    public Hive(String name, String location) {
        this.name = name;
        this.location = location;
        // color !!!
        this.primaryPollenSource = null;
        this.secondaryPollenSource = null;
        this.notes = null;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPrimaryPollen() {
        return this.primaryPollenSource;
    }

    public String getSecondaryPollen() {
        return this.secondaryPollenSource;
    }

    public String getNotes() {
        return this.notes;
    }
}
