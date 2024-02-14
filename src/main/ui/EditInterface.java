package ui;

import model.Hive;

import java.util.Scanner;

// The edit interface of an individual hive. Handles actions involving the modification of individual hives in hives
// (list of hives) including the editing of a name, location, color, and more fields declared in Hive.java.
public class EditInterface {
    private final Hive hive;

    // REQUIRES: hive (h) is not null
    // EFFECTS: Constructor for EditInterface with a given hive (h)
    public EditInterface(Hive h) {
        this.hive = h;
    }

    // EFFECTS: Initiates the interface to edit this hive
    public void enterEditHiveInterface(Scanner scanner) {
        System.out.println("You are now editing the " + hive.getName() + " hive.");
        handleInput(scanner);
    }

    // EFFECTS: Deciphers the action to perform depending on the input
    //          if the input is "exit," exit out of the method
    //          if the input is not an available field, print "Invalid input" and prompt again for another input
    private void handleInput(Scanner scanner) {
        while (true) {
            System.out.println(hive.returnAllFieldValues());
            System.out.println("Select a field to edit (case sensitive): "
                    + "[name, location, color, primary pollen source, secondary pollen source, notes]"
                    + "\n enter 'exit' to stop editing this hive");
            String fieldSelection = scanner.next();
            if (fieldSelection.equals("exit")) {
                break;
            } else if (!hive.getAvailableFields().contains(fieldSelection)) {
                System.out.println("Invalid input.");
                continue;
            }
            System.out.println("Enter the " + fieldSelection + ":");
            String input = scanner.next();

            editDesiredField(fieldSelection, input);
        }
    }

    // REQUIRES: field is "name," "location," "color," "primary pollen source," "secondary pollen source," or "notes"
    // EFFECTS: Manages which action to perform on the hive depending on the value inputted.
    //          Depending on the input, edit*() is called.
    private void editDesiredField(String field, String value) {
        if (field.equals("name")) {
            editName(value);
        } else if (field.equals("location")) {
            editLocation(value);
        } else if (field.equals("color")) {
            editColor(value);
        } else if (field.equals("primary pollen source")) {
            editPrimaryPollenSource(value);
        } else if (field.equals("secondary pollen source")) {
            editSecondaryPollenSource(value);
        } else if (field.equals("notes")) {
            editNotes(value);
        }
    }

    // EFFECTS: Changes the name field of the hive to be the name inputted
    //          if the name is "exit" or "stop," stop process early as they are invalid names, otherwise change the name
    private void editName(String name) {
        // "exit" and "stop" are invalid names because they're words used to interact with the program
        if (name.equals("exit") || name.equals("stop")) {
            System.out.println("This is not a valid name!");
        } else {
            hive.setName(name);
        }
    }

    // EFFECTS: Changes the location field of the hive to be the location inputted
    private void editLocation(String location) {
        hive.setLocation(location);
    }

    // EFFECTS: Changes the color field of the hive to be the color inputted
    private void editColor(String color) {
        hive.setColor(color);
    }

    // EFFECTS: Changes the primary pollen source field of the hive to be the pollen inputted
    //          if the user attempts to change the primary pollen source to "Unspecified" without changing the secondary
    //             pollen source to "Unspecified" first, stop the process early and print a message describing the
    //             problem
    private void editPrimaryPollenSource(String pollen) {
        if (!hive.getSecondaryPollen().equals("Unspecified") && pollen.equals("Unspecified")) {
            System.out.println("You cannot change primary pollen source to 'Unspecified' until you have changed "
                    + "secondary pollen source to 'Unspecified'.");
        } else {
            hive.setPrimaryPollenSource(pollen);
        }
    }

    // EFFECTS: Changes the secondary pollen source field of the hive to be the pollen inputted
    //          if the primary pollen source is "Unspecified", stop the process early and print a message describing the
    //             problem, otherwise change the secondary pollen source to the pollen inputted
    private void editSecondaryPollenSource(String pollen) {
        if (hive.getPrimaryPollen().equals("Unspecified")) {
            System.out.println("You cannot specify a secondary pollen source until "
                    + "you have specified a primary pollen source.");
        } else {
            hive.setSecondaryPollenSource(pollen);
        }
    }

    // EFFECTS: Changes the notes field of the hive to be the note inputted
    private void editNotes(String note) {
        hive.setNotes(note);
    }

    // getter method
    // EFFECTS: returns the hive that is currently being edited
    public Hive getHive() {
        return this.hive;
    }
}
