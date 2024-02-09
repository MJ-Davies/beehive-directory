package model;

import java.util.LinkedList;

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

    public LinkedList<Hive> getListOfHives() {
        return this.listOfHives;
    }
}
