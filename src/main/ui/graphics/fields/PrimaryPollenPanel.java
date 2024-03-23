package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays primary pollen source field for the inputted hive and handles any additional requirements needed
public class PrimaryPollenPanel extends HiveFields {
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
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        hive.setPrimaryPollenSource(input.getText());
        directory.mainScreenBody();
    }
}
