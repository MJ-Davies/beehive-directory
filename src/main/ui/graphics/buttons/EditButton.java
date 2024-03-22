package ui.graphics.buttons;

import model.Hive;

import ui.graphics.DirectoryFrame;

// A button for editing a hive
public class EditButton extends MyButton {
    private Hive hive;

    // EFFECTS: Constructor for EditButton
    public EditButton(String text, DirectoryFrame frame, Hive hive) {
        super(text, frame);
        this.hive = hive;
        this.addActionListener(e -> frame.goToEditScreen(hive));
    }


}
