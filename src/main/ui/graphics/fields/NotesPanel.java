package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Displays notes field for the inputted hive
public class NotesPanel extends HiveField {
    // EFFECTS: Constructor for NotesPanel
    public NotesPanel(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        super(posX, posY, width, height, hive, directory);
    }

    // EFFECTS: Returns the appropriate name for the "Notes" field
    @Override
    public String getFieldName() {
        return "Notes";
    }

    // EFFECTS: Returns the current value for the notes of the hive
    @Override
    public String fieldValue() {
        return hive.getNotes();
    }

    // MODIFIES: this, input
    // EFFECTS: Sets the notes of the hive to be the given input
    //          If the input is an empty string, set to EMPTY_DEFAULT_MSG
    @Override
    public void updateField(ActionEvent e, JTextField input) {
        if (input.getText().isEmpty()) {
            hive.setNotes(EMPTY_DEFAULT_MSG);
            input.setText(EMPTY_DEFAULT_MSG);
        } else {
            hive.setNotes(input.getText());
        }
        directory.updateMainScreenBody();
        confirmMessage(getFieldName());
    }
}
