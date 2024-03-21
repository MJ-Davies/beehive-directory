package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for loading the file into the current program
public class LoadButton extends MyButton {
    
    // EFFECTS: Constructor for LoadButton
    public LoadButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.loadHives());
    }
}
