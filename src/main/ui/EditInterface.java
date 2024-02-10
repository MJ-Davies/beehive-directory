package ui;

import model.Hive;
import java.util.Scanner;
import java.util.LinkedList;

public class EditInterface extends Directory {
    private Hive hive;

    // EFFECTS: Constructor for EditInterface with a given hive (h)
    public EditInterface(Hive h) {
        this.hive = h;
    }

    // EFFECTS: Initiates the menu to edit this hive
    public void enterEditHiveInterface(Scanner scanner) {
        System.out.println("You are now editing the " + hive.getName() + " hive.");
        handleInput(scanner);
    }

    // EFFECTS: Deciphers the action to perform depending on the input
    //          if the input is "exit," exit out of the method
    //          if the input is not an available field, print "Invalid input" and prompt again for another input
    public void handleInput(Scanner scanner) {
        String fieldSelection = "";
        String input = "";
        while (true) {
            hive.printAllFieldValues();
            System.out.println("Select a field to edit (case sensitive): "
                    + "[name, location, color, primary pollen source, secondary pollen source, notes]"
                    + "\n enter 'exit' to stop editing this hive");
            fieldSelection = scanner.next();
            if (fieldSelection.equals("exit")) {
                break;
            } else if (!hive.getAvailableFields().contains(fieldSelection)) {
                System.out.println("Invalid input.");
                continue;
            }
            System.out.println("Enter the " + fieldSelection + ":");
            input = scanner.next();

            editDesiredField(fieldSelection, input);
        }
    }

    public void editDesiredField(String field, String value) {
        if (field.equals("name")) {
            editName(value);
        } else if (field.equals("location")) {
            editLocation(value);
        } else if (field.equals("color")) {
            // stub
        } else if (field.equals("primary pollen source")) {
            editPrimaryPollenSource(value);
        } else if (field.equals("secondary pollen source")) {
            editSecondaryPollenSource(value);
        } else if (field.equals("notes")) {
            editNotes(value);
        }
    }

    // EFFECTS: Changes the name field of the hive to be the name inputted
    //          if the name is "exit" or "stop," stop process early, otherwise change the name
    public void editName(String name) {
        if (name.equals("exit") || name.equals("stop")) {
            System.out.println("This is not a valid name!");
        } else {
            hive.setName(name);
        }
    }

    // EFFECTS: Changes the location field of the hive to be the location inputted
    public void editLocation(String location) {
        hive.setLocation(location);
    }

    //public void editColor !!! get bak to later

    // EFFECTS: Changes the primary pollen source field of the hive to be the pollen inputted
    public void editPrimaryPollenSource(String pollen) {
        hive.setPrimaryPollenSource(pollen);
    }

    // EFFECTS: Changes the secondary pollen source field of the hive to be the pollen inputted
    //          if the primary pollen source is not specified (null), stop the process early, otherwise change the
    //             secondary pollen source
    public void editSecondaryPollenSource(String pollen) {
        if (hive.getPrimaryPollen().equals("Unspecified")) {
            System.out.println("You cannot specify a secondary pollen source until "
                    + "you have specified a primary pollen source.");
        } else {
            hive.setSecondaryPollenSource(pollen);
        }
    }

    // EFFECTS: Changes the notes field of the hive to be the note inputted
    public void editNotes(String note) {
        hive.setNotes(note);
    }

    // getter method
    public Hive getHive() {
        return this.hive;
    }
}