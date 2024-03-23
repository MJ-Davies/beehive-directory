package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays name field for the inputted hive and handles any additional requirements needed
public class NamePanel extends HiveField {
    // EFFECTS: Constructor for NamePanel
    public NamePanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Name" field
    @Override
    public String getFieldName() {
        return "Name";
    }

    // EFFECTS: Returns the current value of the name of the hive
    @Override
    public String fieldValue() {
        return hive.getName();
    }

    // MODIFIES: this
    // EFFECTS: Sets the name of the hive to be the given input
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if (input.getText().isEmpty()) {
            hive.setName(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setName(input.getText());
        }
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}
