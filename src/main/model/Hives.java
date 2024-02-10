package model;

import java.util.LinkedList;
import java.util.function.Function;

public class Hives {
    private LinkedList<Hive> listOfHives;

    public Hives() {
        this.listOfHives = new LinkedList<Hive>();
    }

    // REQUIRES: Hive does not already exist with same name
    // MODIFIES: this
    // EFFECTS: Adds a hive with a valid name and location and prints a message declaring that hive has been added
    public void addHive(String name, String location) {
        listOfHives.addLast(new Hive(name, location));
        System.out.println(name + " has been added to the directory.");
    }

    // REQUIRES: Hive already exists with the same name
    // MODIFIES: this
    // EFFECTS: Removes a hive with the same name and prints a message declaring that hive has been removed
    //         if name does not exist, print "Hive name does not exist."
    public void removeHive(String name) {
        listOfHives.remove(getPositionInHives(name));
        System.out.println(name + " has been removed from the directory.");
    }

    // EFFECTS: Returns the zero-based index position of a hive given the name
    //          if hive does not exist, return -1
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

    // EFFECTS: Returns a concatenated String of all hive names in this directory
    public String returnAllHiveNames() {
        if (listOfHives.isEmpty()) {
            return "There are no hives in this directory.";
        }
        String result = "Hive names: ";
        for (Hive h: listOfHives) {
            if (listOfHives.size() == listOfHives.indexOf(h) + 1) {
                result += h.getName();
            } else {
                result += h.getName() + ", ";
            }
        }
        return result;
    }

    // EFFECT: Prints location value and number of frequency in the form of "Location: frequency"
    public void printLocationMetrics() {
        // uniqueLocations and frequencyOfLocations are in tandem
        LinkedList<String> uniqueLocations = getDistinctX(Hive::getLocation);
        LinkedList<Number> frequencyOfLocations = getFrequencyOfX(Hive::getLocation, uniqueLocations);

        String message = "";
        for (int i = 0; i < uniqueLocations.size(); i++) {
            String printLine = "";
            message += uniqueLocations.get(i) + ": " + frequencyOfLocations.get(i) + "\n";
        }
        System.out.println("UNIQUE LOCATIONS: \n================\n" + message);
    }

    // MODIFIES: this
    // EFFECT: Sorts hives by pollen in the order of primary, secondary, and irrelevant
    public void sortByPollen(String type) {
        LinkedList<String> uniquePrimaryPollens = getDistinctX(Hive::getPrimaryPollen);
        LinkedList<String> uniqueSecondaryPollens = getDistinctX(Hive::getSecondaryPollen);

        LinkedList<Hive> updatedList = new LinkedList<Hive>();
        int secondaryStartPos = 0;
        for (Hive h:listOfHives) {
            Hive currentHive = h;

            if (h.getPrimaryPollen().equals(type)) {
                updatedList.addFirst(currentHive);
                secondaryStartPos++;
            } else if (h.getSecondaryPollen().equals(type)) {
                updatedList.add(secondaryStartPos, currentHive);
            } else {
                updatedList.addLast(currentHive);
            }
        }
        listOfHives = updatedList;
    }

    // REQUIRES: getFunc returns a value
    // EFFECTS: Returns a list of strings of the total amount of distinct X in a hive where X is the distinct field
    //          being collected. getFunction is a higher order function
    // source: https://stackoverflow.com/questions/29041708/high-order-functions-in-java
    public LinkedList<String> getDistinctX(Function<Hive, String> getFunc) {
        LinkedList<String> listX = new LinkedList<String>();
        for (Hive h:listOfHives) {
            String objectX = getFunc.apply(h);
            if (!listX.contains(objectX)) {
                listX.add(objectX);
            }
        }
        return listX;
    }

    // REQUIRES: getFunc returns a value
    // EFFECTS: Returns a list of numbers of the amount of occurrences for each distinct X where X is the field of
    //          interest
    // source: https://stackoverflow.com/questions/29041708/high-order-functions-in-java
    public LinkedList<Number> getFrequencyOfX(Function<Hive, String> getFunc, LinkedList<String> distinctList) {
        LinkedList<Number> frequencyList = new LinkedList<Number>();
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

    public void viewPollens() {
        String message = "";
        for (Hive h:listOfHives) {
            message += h.getName() + ": " + h.getPrimaryPollen() + ", " + h.getSecondaryPollen() + "\n";
        }
        System.out.println(message);
    }

    // getters:
    public LinkedList<Hive> getListOfHives() {
        return this.listOfHives;
    }
}
