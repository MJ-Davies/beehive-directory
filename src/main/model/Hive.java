package model;

import java.util.LinkedList;

public class Hive {
    private String name;
    private String location;
    // Color !!!
    private String primaryPollenSource;
    private String secondaryPollenSource;
    private String notes;

    private LinkedList<String> availableFields;

    // EFFECT: Constructs a hive with a specified name and location
    public Hive(String name, String location) {
        this.name = name;
        this.location = location;
        // color !!!
        this.primaryPollenSource = "Unspecified";
        this.secondaryPollenSource = "Unspecified";
        this.notes = "Unspecified";
        this.availableFields = new LinkedList<String>();
        addAllAvailableFields();
    }

    // MODIFIES: this
    // EFFECT: creates a list of all the hive fields present in this
    public void addAllAvailableFields() {
        availableFields.add("name");
        availableFields.add("location");
        availableFields.add("color");
        availableFields.add("primary pollen source");
        availableFields.add("secondary pollen source");
        availableFields.add("notes");
    }

    // EFFECTS: prints all of the fields and their values into the console
    public void printAllFieldValues() {
        System.out.println("Name: " + name
                + "\n Location: " + location
                + "\n Color: " + "I gotta add this implementation!!!"
                + "\n Primary Pollen Source: " + primaryPollenSource
                + "\n Secondary Pollen Source: " + secondaryPollenSource
                + "\n Notes: " + notes);
    }

    // setter methods:
    // MODIFIES: this
    // EFFECTS: sets the name of the Hive to be the inputted name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the location of the Hive to be the inputted location
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES: this
    // EFFECTS: sets the primary pollen source of the Hive to be the inputted primary pollen source
    public void setPrimaryPollenSource(String primaryPollenSource) {
        this.primaryPollenSource = primaryPollenSource;
    }

    // MODIFIES: this
    // EFFECTS: sets the secondary pollen source of the Hive to be the inputted secondary pollen source
    public void setSecondaryPollenSource(String secondaryPollenSource) {
        this.secondaryPollenSource = secondaryPollenSource;
    }

    // MODIFIES: this
    // EFFECTS: sets the notes of the Hive to be the inputted notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    // getter methods:
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

    public LinkedList<String> getAvailableFields() {
        return this.availableFields;
    }
}
