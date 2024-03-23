package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// A button for adding hives
public class AddButton extends DirectoryButton {
    // EFFECTS: Constructor for AddButton, adds hive upon pressing
    public AddButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.addHive());
    }
}
