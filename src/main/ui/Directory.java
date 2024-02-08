package ui;

import model.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Directory {
    private LinkedList<Hive> hives;
    private Scanner scanner; // this will scan the user input

    // EFFECTS: Constructs a hive directory
    public Directory() {
        this.hives = new LinkedList<Hive>();
        this.scanner = new Scanner(System.in);
        scanner.useDelimiter("\n"); // when the user adds a new line, the input is processed
    }

    // EFFECTS: Runs the directory application
    public void runDirectory() {
        String input = "";
        while (true) {
            System.out.println("Welcome to the beehive directory, please select an action:"
                    + "[add, remove, view, edit, quit]");
            input = scanner.next();
            System.out.println("You selected: " + input);

            if (input.equals("quit")) {
                System.out.println("Closing directory...");
                break;
            }

            handleInput(input);
        }
    }

    // EFFECTS: Deciphers the action to perform depending on the input (in)
    //          if input is "add," then ask for name and location to add a hive using the addHive helper function
    //          if input is "remove," then ask for name and remove the hive using the removeHive helper function
    //          if input is "view," then !!!
    //          if input is "edit," then !!!
    //          if none of the inputs were invalid, then print "Invalid input."
    public void handleInput(String in) {
        String name = "";

        if (in.equals("add")) {
            String location = "";

            name = getName(in);
            System.out.println("Type the location of the hive:");
            location = scanner.next();

            addHive(name, location);
        } else if (in.equals("remove")) {
            name = getName(in);

            removeHive(name);
        } else if (in.equals("view")) {
            System.out.println(returnAllHiveNames());
        } else if (in.equals("edit")) {
            // stub
            System.out.println("edit");
        } else {
            System.out.println("Invalid input.");
        }
    }

    // REQUIRES: Hive does not already exist with same name
    // MODIFIES: this
    // EFFECTS: Adds a hive with a valid name and location and prints a message declaring that hive has been added
    public void addHive(String name, String location) {
        hives.addLast(new Hive(name, location));
        System.out.println(name + " has been added to the directory.");
    }

    // REQUIRES: Hive already exists with the same name
    // MODIFIES: this
    // EFFECTS: Removes a hive with the same name and prints a message declaring that hive has been removed
    //         if name does not exist, print "Hive name does not exist."
    public void removeHive(String name) {
        hives.remove(getPositionInHives(name));
        System.out.println(name + " has been removed from the directory.");
    }

    // EFFECTS: Returns the zero-based index position of a hive given the name
    //          if hive does not exist, return -1
    public int getPositionInHives(String name) {
        for (Hive h:hives) {
            if (h.getName().equals(name)) {
                return hives.indexOf(h);
            }
        }
        return -1;
    }

    // EFFECT: returns the inputted name
    //         if operation is add, the inputted name must not already exist, otherwise prompt again for input
    //         if operation is not add, the inputted name must exist, otherwise prompt again for input
    public String getName(String operation) {
        String name = "";

        final String addMessage = "That hive name already exists";
        final String removeOrEditMessage = "That hive name does not exist";

        while (true) {
            System.out.println("Type the name of the hive (case sensitive):");
            name = scanner.next();
            if (!hiveExists(name) && operation.equals("add")) {
                break;
            } else if (hiveExists(name) && (operation.equals("remove") || operation.equals("edit"))) {
                break;
            }
            if (operation.equals("add")) {
                System.out.println(addMessage + ", please pick another name.");
            } else {
                System.out.println(removeOrEditMessage + ", please pick another name.");
            }
        }
        return name;
    }

    // EFFECTS: Returns true if the given hive name exists in hives. Otherwise return false.
    public Boolean hiveExists(String name) {
        for (Hive h:hives) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Returns a concatenated String of all hive names in this directory
    public String returnAllHiveNames() {
        if (hives.isEmpty()) {
            return "There are no hives in this directory.";
        }
        String result = "";
        for (Hive h:hives) {
            if (hives.size() == hives.indexOf(h) + 1) {
                result += h.getName();
            } else {
                result += h.getName() + ", ";
            }
        }
        return result;
    }

    public LinkedList<Hive> getHives() {
        return this.hives;
    }
}
