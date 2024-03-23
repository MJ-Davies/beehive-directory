package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays location field for the inputted hive and handles any additional requirements needed
public class LocationPanel extends HiveField {
    // EFFECTS: Constructor for LocationPanel
    public LocationPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Location" field
    @Override
    public String getFieldName() {
        return "Location";
    }

    // EFFECTS: Returns the current value of the location of the hive
    @Override
    public String fieldValue() {
        return hive.getLocation();
    }

    // MODIFIES: this, input
    // EFFECTS: Sets the location of the hive to be the given input
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if (input.getText().isEmpty()) {
            hive.setLocation(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setLocation(input.getText());
        }
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}
