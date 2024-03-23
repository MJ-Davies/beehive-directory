package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays notes field for the inputted hive and handles any additional requirements needed
public class NotesPanel extends HiveFields {
    // EFFECTS: Constructor for NotesPanel
    public NotesPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Additional Notes" field
    @Override
    public String getFieldName() {
        return "Additional Notes";
    }

    // EFFECTS: Returns the current value for the notes of the hive
    @Override
    public String fieldValue() {
        return hive.getNotes();
    }

    // MODIFIES: this
    // EFFECTS: Sets the notes of the hive to be the given input
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        hive.setNotes(input.getText());
        directory.mainScreenBody();
    }
}
