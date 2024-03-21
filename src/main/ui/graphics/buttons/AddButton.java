package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// A button for adding hives
public class AddButton extends MyButton {

    // EFFECTS: Constructor for AddButton
    public AddButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.addHive());
    }
}
