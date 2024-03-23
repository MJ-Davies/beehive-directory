package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Displays color field for the inputted hive and handles any additional requirements needed
public class ColorPanel extends HiveField {
    // EFFECTS: Constructor for ColorPanel
    public ColorPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // REQUIRES: container has a FlowLayout (default layout)
    // MODIFIES: container
    // EFFECTS: Adds the appropriate field name as a JLabel to container, lists the available colours
    @Override
    public void addFieldName(JPanel container) {
        JLabel fieldName = new JLabel(getFieldName() + " [golden, amber, light, dark, other]");
        fieldName.setFont(new Font(null, Font.PLAIN, 15));
        container.add(fieldName);
    }

    // EFFECTS: Returns the appropriate name for the "Color" field
    @Override
    public String getFieldName() {
        return "Color";
    }

    // EFFECTS: Returns the current value of the color of the hive
    @Override
    public String fieldValue() {
        return hive.getColorInString();
    }

    // MODIFIES: this, input
    // EFFECTS: Sets the color of the hive to be the given input
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if (input.getText().isEmpty()) {
            hive.setColor(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setColor(input.getText());
        }
        input.setText(hive.getColorInString());
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}