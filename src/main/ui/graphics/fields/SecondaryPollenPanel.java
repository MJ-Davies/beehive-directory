package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays secondary pollen source field for the inputted hive and handles any additional requirements needed
public class SecondaryPollenPanel extends HiveFields {
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
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        hive.setSecondaryPollenSource(input.getText());
        directory.mainScreenBody();
    }
}
