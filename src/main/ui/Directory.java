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
    //          if input is "view," then returnAllHiveNames
    //          if input is "edit," then go into the hive's editing interface with the enterEditHiveInterface function
    //          if none of the inputs were invalid, then print "Invalid input."
    //          if input is null, end the method process early
    public void handleInput(String in) {
        if (in.equals("view")) {
            System.out.println(hives.returnAllHiveNames());
            return;
        }

        String name = getName(in);

        if (name == null) {
            return;
        }

        if (in.equals("add")) {
            String location = "";
            System.out.println("Type the location of the hive:");
            location = scanner.next();

            hives.addHive(name, location);
        } else if (in.equals("remove")) {
            hives.removeHive(name);
        } else if (in.equals("edit")) {
            Hive hive = hives.getListOfHives().get(hives.getPositionInHives(name));
            EditInterface hiveInterface = new EditInterface(hive);
            hiveInterface.enterEditHiveInterface(scanner);
        } else {
            System.out.println("Invalid input.");
        }
    }

    // EFFECT: returns the inputted name
    //         if operation is add, the inputted name must not already exist, otherwise prompt again for input
    //         if operation is not add, the inputted name must exist, otherwise prompt again for input
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
}
