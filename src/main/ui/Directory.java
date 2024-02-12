package ui;

import model.Hives;
import model.Hive;

import java.util.Scanner;

public class Directory {
    private Hives hives;
    private Scanner scanner; // this will scan the user input

    // EFFECTS: Constructs a hive directory
    public Directory() {
        this.hives = new Hives();
        this.scanner = new Scanner(System.in);
        scanner.useDelimiter("\n"); // when the user adds a new line, the input is processed
    }

    // EFFECTS: Runs the directory application by prompting for which actions the user wants to take
    public void runDirectory() {
        while (true) {
            System.out.println("Welcome to the beehive directory, please select an action:"
                    + "[add, remove, view, edit, metrics, sort, quit]");
            String input = scanner.next();
            System.out.println("You selected: " + input);

            if (input.equals("quit")) {
                System.out.println("Closing directory...");
                break;
            }

            handleInput(input);
        }
    }

    // EFFECTS: Deciphers the action to perform depending on the input (in)
    //          if input is "view," then print all hive names using the returnAllHiveNames helper function
    //          if input is "metrics," then request for metrics by using the requestMetrics helper function
    //          if input is "sort," then start a request for sorting
    //          if none of the inputs were invalid, then print "Invalid input."
    //          if getName(in) is null, then end the process early
    public void handleInput(String in) {
        if (in.equals("view")) {
            System.out.println(hives.returnAllHiveNames());
            return;
        } else if (in.equals("metrics")) {
            requestMetrics();
            return;
        } else if (in.equals("sort")) {
            startSort();
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

    // REQUIRES: action is "add" "remove," or "edit"
    // EFFECTS: Applies the directory action depending on the inputted action (action) and name (name)
    //          if input is "add," then ask for name and location to add a hive using the addHive helper function
    //          if input is "remove," then ask for name and remove the hive using the removeHive helper function
    //          if input is "edit," then go into the hive's editing interface with the enterEditHiveInterface function
    public void handleInputsNeedingNames(String action, String name) {
        if (action.equals("add")) {
            System.out.println("Type the location of the hive:");
            String location = scanner.next();
            hives.addHive(name, location);
        } else if (action.equals("remove")) {
            hives.removeHive(name);
        } else if (action.equals("edit")) {
            Hive hive = hives.getListOfHives().get(hives.getPositionInHives(name));
            EditInterface hiveInterface = new EditInterface(hive);
            hiveInterface.enterEditHiveInterface(scanner);
        }
    }

    // EFFECTS: requests the user for a hive name
    //         if operation is add, the inputted hive name must not already exist, otherwise prompt again for input
    //         if operation is not add, the inputted hive name must exist, otherwise prompt again for input
    //         if the input is "stop," then set name to null and end the process with no further actions performed
    public String getName(String operation) {
        String name = "";

        final String addMessage = "That hive name already exists";
        final String removeOrEditMessage = "That hive name does not exist";

        while (true) {
            System.out.println(hives.returnAllHiveNames());
            System.out.println("Type the name of the hive (case sensitive), end this action by entering 'stop':");
            name = scanner.next();
            if (name.equals("stop")) {
                name = null;
                break;
            }
            if (!hives.hiveExists(name) && operation.equals("add")) {
                break;
            } else if (hives.hiveExists(name) && (operation.equals("remove") || operation.equals("edit"))) {
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

    // EFFECTS: Requests for the metrics (location, color, primary pollen, secondary pollen) of this directory
    public void requestMetrics() {
        System.out.println(hives.returnMetrics());
    }

    // EFFECTS: Starts the process of sorting by requesting which type to sort by
    //          if the type is "pollen source," then sortByPollen
    public void startSort() {
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

    // getters:
    public Hives getHives() {
        return this.hives;
    }
}
