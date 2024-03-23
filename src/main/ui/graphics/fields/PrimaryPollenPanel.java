package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays primary pollen source field for the inputted hive and handles any additional requirements needed
public class PrimaryPollenPanel extends HiveField {
    // EFFECTS: Constructor for PrimaryPollenPanel
    public PrimaryPollenPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Primary Pollen Source" field
    @Override
    public String getFieldName() {
        return "Primary Pollen Source";
    }

    // EFFECTS: Returns the current value of the primary pollen source of the hive
    @Override
    public String fieldValue() {
        return hive.getPrimaryPollen();
    }

    // MODIFIES: this
    // EFFECTS: Sets the primary pollen source of the hive to be the given input
    //          If the input is an empty string or EMPTY_DEFAULT_MSG and the secondary pollen source is unspecified,
    //             end the method early and show a warning message
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    //          Otherwise, set the primary pollen field to the input
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if ((input.getText().equals(EMPTY_DEFAULT_MSG) || input.getText().isEmpty())
                && !hive.getSecondaryPollen().equals("Unspecified")) {
            JOptionPane.showMessageDialog(null,
                    "You cannot unspecify a primary pollen source until you have unspecified the secondary"
                            + " pollen source.",
                    "Unsuccessful Edit", JOptionPane.WARNING_MESSAGE);
            input.setText(hive.getPrimaryPollen());
            return;
        }
        if (input.getText().isEmpty()) {
            hive.setPrimaryPollenSource(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setPrimaryPollenSource(input.getText());
        }
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}
