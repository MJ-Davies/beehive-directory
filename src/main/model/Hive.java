package model;

import java.util.LinkedList;

public class Hive {
    private String name;
    private String location;
    private Color color;
    private String primaryPollenSource;
    private String secondaryPollenSource;
    private String notes;

    private LinkedList<String> availableFields;

    // EFFECTS: Constructs a hive with a specified name and location
    public Hive(String name, String location) {
        this.name = name;
        this.location = location;
        this.color = Color.OTHER; // atomic distinct
        this.primaryPollenSource = "Unspecified";
        this.secondaryPollenSource = "Unspecified";
        this.notes = "Unspecified";
        this.availableFields = new LinkedList<String>();
        addAllAvailableFields();
    }

    // MODIFIES: this
    // EFFECTS: adds to availableFields of all the hive fields present in this
    public void addAllAvailableFields() {
        availableFields.add("name");
        availableFields.add("location");
        availableFields.add("color");
        availableFields.add("primary pollen source");
        availableFields.add("secondary pollen source");
        availableFields.add("notes");
    }

    // EFFECTS: returns all of the fields and their values into the console
    public String returnAllFieldValues() {
        return "Name: " + name
                + "\n Location: " + location
                + "\n Color [golden, amber, light, dark, other]: " + color.colorToString()
                + "\n Primary Pollen Source: " + primaryPollenSource
                + "\n Secondary Pollen Source: " + secondaryPollenSource
                + "\n Notes: " + notes;
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
    // EFFECTS: sets the color of the Hive to be the inputted color.
    //          Distinct values including "golden," "amber," "light," and "dark" are translated to the Color enumeration
    //          If the inputted color does not match any distinct values, color.OTHER is assigned.
    public void setColor(String col) {
        if (col.equals("golden")) {
            this.color = color.GOLDEN;
        } else if (col.equals("amber")) {
            this.color = color.AMBER;
        } else if (col.equals("light")) {
            this.color = color.LIGHT;
        } else if (col.equals("dark")) {
            this.color = color.DARK;
        } else {
            this.color = color.OTHER;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the primary pollen source of the Hive to be the inputted primary pollen source
    public void setPrimaryPollenSource(String primaryPollenSource) {
        this.primaryPollenSource = primaryPollenSource;
    }

    // REQUIRES: primaryPollenSource is not Unspecified or null
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

    // EFFECTS: Returns the color of Color type to string
    public String getColorInString() {
        return this.color.colorToString();
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public Color getColor() {
        return this.color;
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
