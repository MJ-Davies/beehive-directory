package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// Hive represents a single unique hive which contains fields such as name, location, color, primary pollen source,
// secondary pollen source, and notes. Mostly consists of simple getter and setter methods.
public class Hive implements Writable {
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
        this.availableFields = new LinkedList<>();
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

    // EFFECTS: returns all of the fields and their values in the form of "field: value." Each field is separated on a
    // new line
    public String returnAllFieldValues() {
        return " Name: " + name
                + "\n Location: " + location
                + "\n Color [golden, amber, light, dark, other]: " + color.colorToString()
                + "\n Primary Pollen Source: " + primaryPollenSource
                + "\n Secondary Pollen Source: " + secondaryPollenSource
                + "\n Notes: " + notes;
    }

    // EFFECTS: Converts hive into a Json Object
    // Modeled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/Thingy.java
    @Override
    public JSONObject toJson() {
        JSONObject hiveJsonObject = new JSONObject();
        hiveJsonObject.put("name", name);
        hiveJsonObject.put("location", location);
        hiveJsonObject.put("color", color.colorToString());
        hiveJsonObject.put("primaryPollenSource", primaryPollenSource);
        hiveJsonObject.put("secondaryPollenSource", secondaryPollenSource);
        hiveJsonObject.put("notes", notes);
        return hiveJsonObject;
    }

    // setter methods:
    // MODIFIES: this
    // EFFECTS: sets the name of the Hive to be the inputted name
    public void setName(String name) {
        EventLog.getInstance().logEvent(new Event("The name of " + this.name + " has been changed to "
                + name));
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the location of the Hive to be the inputted location
    public void setLocation(String location) {
        EventLog.getInstance().logEvent(new Event("The location of " + this.name + " has been changed to "
                + location));
        this.location = location;
    }

    // MODIFIES: this
    // EFFECTS: sets the color of the Hive to be the inputted color.
    //          Distinct values including "golden," "amber," "light," and "dark" are translated to the Color enumeration
    //          If the inputted color does not match any distinct values, color.OTHER is assigned.
    public void setColor(String col) {
        if (col.equals("golden")) {
            this.color = Color.GOLDEN;
        } else if (col.equals("amber")) {
            this.color = Color.AMBER;
        } else if (col.equals("light")) {
            this.color = Color.LIGHT;
        } else if (col.equals("dark")) {
            this.color = Color.DARK;
        } else {
            this.color = Color.OTHER;
        }
        EventLog.getInstance().logEvent(new Event("The color of " + this.name + " has been changed to "
                + this.color.colorToString()));
    }

    // REQUIRES: primaryPollenSource input is not "Unspecified" when secondaryPollenSource is set to anything other than
    //           "Unspecified" or null
    // MODIFIES: this
    // EFFECTS: sets the primary pollen source of the Hive to be the inputted primary pollen source
    public void setPrimaryPollenSource(String primaryPollenSource) {
        EventLog.getInstance().logEvent(new Event("The primary pollen source of " + this.name
                + " has been changed to " + primaryPollenSource));
        this.primaryPollenSource = primaryPollenSource;
    }

    // REQUIRES: primaryPollenSource is not Unspecified or null
    // MODIFIES: this
    // EFFECTS: sets the secondary pollen source of the Hive to be the inputted secondary pollen source
    public void setSecondaryPollenSource(String secondaryPollenSource) {
        EventLog.getInstance().logEvent(new Event("The secondary pollen source of " + this.name
                + " has been changed to " + secondaryPollenSource));
        this.secondaryPollenSource = secondaryPollenSource;
    }

    // MODIFIES: this
    // EFFECTS: sets the notes of the Hive to be the inputted notes
    public void setNotes(String notes) {
        EventLog.getInstance().logEvent(new Event("The notes of " + this.name + " has been changed to "
                + notes));
        this.notes = notes;
    }

    // getter methods:
    // EFFECTS: Returns the color of Color type to string
    public String getColorInString() {
        return this.color.colorToString();
    }

    // EFFECTS: Returns the name of this hive
    public String getName() {
        return this.name;
    }

    // EFFECTS: Returns the location of this hive
    public String getLocation() {
        return this.location;
    }

    // EFFECTS: Returns the color of this hive
    public Color getColor() {
        return this.color;
    }

    // EFFECTS: Returns the primary pollen source of this hive
    public String getPrimaryPollen() {
        return this.primaryPollenSource;
    }

    // EFFECTS: Returns the secondary pollen source of this hive
    public String getSecondaryPollen() {
        return this.secondaryPollenSource;
    }

    // EFFECTS: Returns the notes of this hive
    public String getNotes() {
        return this.notes;
    }

    // EFFECTS: Returns the available fields of this hive
    public LinkedList<String> getAvailableFields() {
        return this.availableFields;
    }
}
