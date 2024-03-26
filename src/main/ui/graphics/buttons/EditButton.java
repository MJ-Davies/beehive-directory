package ui.graphics.buttons;

import model.Hive;

import ui.graphics.DirectoryFrame;

// Button for editing the hive inputted
public class EditButton extends DirectoryButton {
    private Hive hive;

    // EFFECTS: Constructor for EditButton, opens an edit screen upon pressing
    public EditButton(String text, DirectoryFrame frame, Hive hive) {
        super(text, frame);
        this.hive = hive;
        this.addActionListener(e -> frame.goToEditScreen(this.hive));
    }

    // getter methods:
    public Hive getHive() {
        return this.hive;
    }
}
