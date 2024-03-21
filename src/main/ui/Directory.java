package ui;

import model.Hives;
import model.Hive;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphics.*;

import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

// The main interface that the hives directory runs on. Handles actions which are related to Hives (Hives contains a
// list of hives) including add, remove, view, edit, get metrics, and sort.
// Some method were modeled from (related methods include this citation under their specifications)
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
public class Directory {
    private static final String JSON_FILE_DESTINATION = "./data/hives.json";
    private Hives hives;
    private Scanner scanner; // this will scan the user input
    private JsonReader reader;
    private JsonWriter writer;

    //private MyFrame thisFrame; // this is to make the MyFrame visible in Directory

    // EFFECTS: Constructs a hive directory
    // Partially modeled from
    // github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    public Directory() {
        this.hives = new Hives();
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n"); // when the user adds a new line, the input is processed
        this.writer = new JsonWriter(JSON_FILE_DESTINATION);
        this.reader = new JsonReader(JSON_FILE_DESTINATION);
        //this.thisFrame = frame;
    }

    // EFFECTS: Runs the directory application by prompting for which actions the user wants to take
    public void runDirectory() {
        //thisFrame.goToMainScreen();
        while (true) {
            System.out.println("Welcome to the beehive directory, please select an action: "
                    + "[add, remove, view, edit, metrics, sort, save, load, quit]");
            String input = scanner.next();
            System.out.println("You selected: " + input);

            if (input.equals("quit")) {
                quitDirectory();
                break;
            }

            handleInput(input);
        }
    }

    // EFFECTS: Prompts the user to save before quitting
    public void quitDirectory() {
        System.out.println("Would you like to save your hives? [type 'y' for yes, any key for no]");
        String input = scanner.next();
        if (input.equals("y")) {
            saveHives();
        }
        System.out.println("Closing directory...");
    }

    // EFFECTS: Deciphers the action to perform on the directory depending on the input (in)
    //          if input is "save", saveHives as a json
    //          if input is "load", loadHives from json
    //          Otherwise, move into handling hive operations
    private void handleInput(String in) {
        if (in.equals("save")) {
            saveHives();
        } else if (in.equals("load")) {
            loadHives();
        } else {
            handleHiveOperations(in);
        }
    }

    // EFFECTS: Deciphers the operation to perform on the hives depending on the input (in)
    //          if input is "view," then print all hive names using the returnAllHiveNames helper function
    //          if input is "metrics," then request for metrics by using the requestMetrics helper function
    //          if input is "sort," then start a request for sorting
    //          if there are no hives and the input is "remove" or "edit," end the process early
    //          if getName(in) is null, then end the process early
    //          if none of the inputs were valid, then print "Invalid input."
    private void handleHiveOperations(String in) {
        if (in.equals("view")) {
            System.out.println(hives.returnAllHiveNames());
            return;
        } else if (in.equals("metrics")) {
            requestMetrics();
            return;
        } else if (in.equals("sort")) {
            startSort();
            return;
        } else if ((in.equals("remove") || in.equals("edit")) && hives.getListOfHives().size() == 0) {
            // Handles remove and edit if there are no hives
            System.out.println(hives.returnAllHiveNames());
            return;
        } else if (in.equals("add") || in.equals("remove") || in.equals("edit")) {
            String name = getName(in);
            if (name == null) {
                return;
            } else {
                handleInputsNeedingNames(in, name);
            }
        } else {
            System.out.println("Invalid input.");
            return;
        }
    }

    // REQUIRES: action is "add," "remove," or "edit." If action is "remove" or "edit," then hives.getListOfHives()
    //           must be non-empty
    // EFFECTS: Applies the directory action depending on the inputted action (action) and name (name)
    //          if input is "add," then ask for name and location to add a hive using the addHive helper function
    //          if input is "remove," then ask for name and remove the hive using the removeHive helper function
    //          if input is "edit," then go into the hive's editing interface with the enterEditHiveInterface function
    private void handleInputsNeedingNames(String action, String name) {
        if (action.equals("add")) {
            System.out.println("Type the location of the hive:");
            String location = scanner.next();
            System.out.println(hives.addHive(name, location));
        } else if (action.equals("remove")) {
            System.out.println(hives.removeHive(name));
        } else if (action.equals("edit")) {
            Hive hive = hives.getListOfHives().get(hives.getPositionInHives(name));
            EditInterface hiveInterface = new EditInterface(hive);
            hiveInterface.enterEditHiveInterface(scanner);
        }
    }

    // EFFECTS: requests the user for a hive name
    //          if operation is add, the inputted hive name must not already exist, otherwise prompt again for input
    //          if operation is not add, the inputted hive name must exist, otherwise prompt again for input
    //          if the input is "stop," then set name to null and end the process with no further actions performed
    //          if the input is "exit," then print a message of it being an invalid name
    private String getName(String operation) {
        String name = "";
        while (true) {
            System.out.println(hives.returnAllHiveNames());
            System.out.println("Type the name of the hive (case sensitive), end this action by entering 'stop':");
            name = scanner.next();
            if (name.equals("stop")) {
                name = null;
                break;
            } else if (name.equals("exit")) {
                // "exit" is an invalid name because it's a word used to interact with the program
                System.out.println("Invalid name.");
                continue;
            } else if (!hives.hiveExists(name) && operation.equals("add")) {
                break;
            } else if (hives.hiveExists(name) && (operation.equals("remove") || operation.equals("edit"))) {
                break;
            }
            if (operation.equals("add")) {
                System.out.println("That hive name already exists, please pick another name.");
            } else {
                System.out.println("That hive name does not exist, please pick another name.");
            }
        }
        return name;
    }

    // EFFECTS: Requests for the metrics (location, color, primary pollen, and secondary pollen of hives) of this
    //          directory
    private void requestMetrics() {
        System.out.println(hives.returnMetrics());
    }

    // EFFECTS: Starts the process of sorting by requesting which type to sort by
    //          if the type is "pollen source," then sortByPollen
    //          otherwise, end the process early and print "Invalid input."
    private void startSort() {
        System.out.println("Select the type of sort: [pollen source]");
        String type = scanner.next();
        if (type.equals("pollen source")) {
            System.out.println("Select the type of pollen:");
            String pollenType = scanner.next();
            hives.sortByPollen(pollenType);
            System.out.println(hives.viewPollens());
        } else {
            System.out.println("Invalid input.");
        }
    }

    // EFFECTS: Saves the hives in this directory to file destination
    // Modelled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    private void saveHives() {
        try {
            writer.open();
            writer.write(hives);
            writer.close();
            System.out.println("Saved hives to " + JSON_FILE_DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE_DESTINATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads hives from file destination
    // Modelled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    private void loadHives() {
        try {
            hives = reader.read();
            System.out.println("Loaded hives from " + JSON_FILE_DESTINATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE_DESTINATION);
        }
    }

    // getter methods:
    // EFFECTS: returns the object "hives"
    public Hives getHives() {
        return this.hives;
    }
}
