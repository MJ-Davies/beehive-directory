package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays secondary pollen source field for the inputted hive and handles any additional requirements needed
public class SecondaryPollenPanel extends HiveField {
    // EFFECTS: Constructor for SecondaryPollenPanel
    public SecondaryPollenPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Secondary Pollen Source" field
    @Override
    public String getFieldName() {
        return "Secondary Pollen Source";
    }

    // EFFECTS: Returns the current value of the secondary pollen source of the hive
    @Override
    public String fieldValue() {
        return hive.getSecondaryPollen();
    }

    // MODIFIES: this
    // EFFECTS: Sets the secondary pollen source of the hive to be the given input
    //          If the primary pollen source is unspecified, end the method early and show a warning message
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    //          Otherwise, set the secondary pollen field to the input
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if (hive.getPrimaryPollen().equals(EMPTY_DEFAULT_MSG)) {
            JOptionPane.showMessageDialog(null,
                    "You cannot specify a secondary pollen source until you have specified a primary pollen"
                            + " first.",
                    "Unsuccessful Edit", JOptionPane.WARNING_MESSAGE);
            input.setText(hive.getSecondaryPollen());
            return;
        }
        if (input.getText().isEmpty()) {
            hive.setSecondaryPollenSource(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setSecondaryPollenSource(input.getText());
        }
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}
