package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for loading the file into the current program
public class LoadButton extends DirectoryButton {
    // EFFECTS: Constructor for LoadButton, loads all the hives upon pressing
    public LoadButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.loadHives());
    }
}
