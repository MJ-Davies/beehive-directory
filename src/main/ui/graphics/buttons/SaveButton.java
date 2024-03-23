package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for saving the current state of the program
public class SaveButton extends DirectoryButton {
    // EFFECTS: Constructor for SaveButton, saves all hives to file upon pressing
    public SaveButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.saveHives());
    }
}
