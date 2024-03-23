package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for saving the current state of the program
public class SaveButton extends DirectoryButtons {

    // EFFECTS: Constructor for SaveButton
    public SaveButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.saveHives());
    }
}
