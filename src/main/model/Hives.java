package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.function.Function;

// Hives contains a list of hives, this class contains methods involving the modification of all the hives in a given
// list or obtaining desired metrics or values including index position of hive and the frequency of certain fields
// for all hives.
public class Hives implements Writable {
    private LinkedList<Hive> listOfHives;

    // EFFECTS: Constructor for Hives
    public Hives() {
        this.listOfHives = new LinkedList<>();
    }

    // REQUIRES: Hive does not already exist with same name
    // MODIFIES: this
    // EFFECTS: Adds a hive to the end of the list with a valid name and location and returns a message declaring that
    //          hive has been added. Adds an event log stating that a hive has been added to this.
    public String addHive(String name, String location) {
        listOfHives.addLast(new Hive(name, location));
        String logMsg = name + " has been added to the directory.";
        EventLog.getInstance().logEvent(new Event(logMsg));
        return logMsg;
    }

    // REQUIRES: Hive already exists with the same name
    // MODIFIES: this
    // EFFECTS: Removes a hive with the same name and returns a message declaring that hive has been removed. Adds an
    //          event log stating that the inputted hive has been removed from this.
    public String removeHive(String name) {
        listOfHives.remove(getPositionInHives(name));
        String logMsg = name + " has been removed from the directory.";
        EventLog.getInstance().logEvent(new Event(logMsg));
        return logMsg;
    }

    // MODIFIES: this
    // EFFECTS: Adds an already existing hive to listOfHives. Adds an event log stating that the hive has been added to
    //          this (i.e. it has been loaded)
    public void addExistingHive(Hive h) {
        listOfHives.addLast(h);
        EventLog.getInstance().logEvent(new Event("Loaded " + h.getName() + " with the location of "
                + h.getLocation() + " to the directory."));
    }

    // REQUIRES: Hive already exists with the same name
    // EFFECTS: Returns the zero-based index position of a hive given the name
    //          otherwise, return -1
    public int getPositionInHives(String name) {
        for (Hive h: listOfHives) {
            if (h.getName().equals(name)) {
                return listOfHives.indexOf(h);
            }
        }
        return -1;
    }

    // EFFECTS: Returns true if the given hive name exists in hives. Otherwise return false.
    public Boolean hiveExists(String name) {
        for (Hive h: listOfHives) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns a concatenated String of all hive names in this Hives with a comma delimiter
    //          if listOfHives is empty, then return "There are no hives in this directory"
    public String returnAllHiveNames() {
        if (listOfHives.isEmpty()) {
            return "There are no hives in this directory.";
        }
        StringBuilder result = new StringBuilder("Hive names: ");
        for (Hive h: listOfHives) {
            if (listOfHives.size() == listOfHives.indexOf(h) + 1) {
                result.append(h.getName());
            } else {
                result.append(h.getName()).append(", ");
            }
        }
        return result.toString();
    }

    // EFFECTS: Return metrics (location, color, primary pollen, secondary pollen) of this hives
    //          if there are no hives, then return "No hives to obtain metrics from."
    public String returnMetrics() {
        if (listOfHives.isEmpty()) {
            return "No hives to obtain metrics from.";
        }
        return getMetricXWithHeader("LOCATIONS", Hive::getLocation)
                + getMetricXWithHeader("COLORS", Hive::getColorInString)
                + getMetricXWithHeader("PRIMARY POLLENS", Hive::getPrimaryPollen)
                + getMetricXWithHeader("SECONDARY POLLENS", Hive::getSecondaryPollen);
    }

    // EFFECTS: Return value X (X is a field of interest determined by getFunc, a higher order function)
    //          and number of frequency in the form of "value: frequency" with the appropriate header
    public String getMetricXWithHeader(String header, Function<Hive, String> getFunc) {
        return header + ": \n================\n" + getMetricX(getFunc, header);
    }

    // EFFECTS: Returns a string for the metrics of a given field specified by the get method (higher order function)
    //          in the form of "value: frequency"
    //          Adds an event log stating that the metrics were obtained for this hive
    public String getMetricX(Function<Hive, String> getFunc, String metricName) {
        LinkedList<String> uniqueX = getDistinctStringX(getFunc);
        LinkedList<Number> frequencyOfX = getFrequencyOfX(getFunc, uniqueX);

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < uniqueX.size(); i++) {
            String line = uniqueX.get(i) + ": " + frequencyOfX.get(i) + "\n";
            message.append(line);
        }
        EventLog.getInstance().logEvent(new Event("The metrics of hives were collected for " + metricName));
        return message + "";
    }

    // EFFECTS: Returns a list of strings of the total amount of distinct X in a hive where X is the field of interest
    //          being collected. getFunc is a higher order function
    public LinkedList<String> getDistinctStringX(Function<Hive, String> getFunc) {
        LinkedList<String> listX = new LinkedList<>();
        for (Hive h:listOfHives) {
            String objectX = getFunc.apply(h);
            if (!listX.contains(objectX)) {
                listX.add(objectX);
            }
        }
        return listX;
    }

    // EFFECTS: Returns a list of numbers of the amount of occurrences for each distinct X where X is the field of
    //          interest. getFunc is a higher order function
    public LinkedList<Number> getFrequencyOfX(Function<Hive, String> getFunc, LinkedList<String> distinctList) {
        LinkedList<Number> frequencyList = new LinkedList<>();
        for (String s:distinctList) {
            int freq = 0;
            for (Hive h:listOfHives) {
                if (getFunc.apply(h).equals(s)) {
                    freq++;
                }
            }
            frequencyList.add(freq);
        }
        return frequencyList;
    }

    // MODIFIES: this
    // EFFECTS: Sorts hives by pollen in the order of primary, secondary, and irrelevant. Adds an event log stating that
    //          all hives have been sorted by the inputted pollen type
    public void sortByPollen(String type) {
        LinkedList<Hive> updatedList = new LinkedList<>();
        int secondaryStartPos = 0;
        for (Hive h:listOfHives) {
            if (h.getPrimaryPollen().equals(type)) {
                updatedList.addFirst(h);
                secondaryStartPos++;
            } else if (h.getSecondaryPollen().equals(type)) {
                updatedList.add(secondaryStartPos, h);
            } else {
                updatedList.addLast(h);
            }
        }
        listOfHives = updatedList;
        EventLog.getInstance().logEvent(new Event("Sorted all hives by pollen source: " + type));
    }

    // EFFECTS: returns a string of the hive's name, primary pollen, and secondary pollen in the form of:
    //          "name: primaryPollen, secondaryPollen\n"
    //          If there are no hives, return "No hives to view pollen."
    //          Adds an event log stating that the hives have been viewed
    public String viewPollens() {
        if (listOfHives.isEmpty()) {
            return "No hives to view pollen.";
        }
        StringBuilder message = new StringBuilder();
        for (Hive h:listOfHives) {
            String line = h.getName() + ": " + h.getPrimaryPollen() + ", " + h.getSecondaryPollen() + "\n";
            message.append(line);
        }
        EventLog.getInstance().logEvent(new Event("Viewed all hives."));
        return message.toString();
    }

    // EFFECTS: Converts hives into a Json object, returns the Json object. Adds an event log stating that all hives
    //          have been saved.
    @Override
    public JSONObject toJson() {
        JSONObject hivesJsonObject = new JSONObject();
        hivesJsonObject.put("hives", hiveToJson());
        EventLog.getInstance().logEvent(new Event("Saved all hives."));
        return hivesJsonObject;
    }

    // EFFECTS: Converts listOfHives into a Json array, returns the Json array
    public JSONArray hiveToJson() {
        JSONArray hiveJsonArray = new JSONArray();
        for (Hive h:listOfHives) {
            hiveJsonArray.put(h.toJson());
        }
        return hiveJsonArray;
    }

    // getters:
    // EFFECTS: returns the listOfHives in this instance of hives
    public LinkedList<Hive> getListOfHives() {
        return this.listOfHives;
    }
}
