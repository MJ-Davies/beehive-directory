package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for sorting all hives
public class SortButton extends DirectoryButton {
    // EFFECTS: Constructor for SortButton, sorts all the hives upon pressing
    public SortButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.sortHives());
    }
}
