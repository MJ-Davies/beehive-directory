package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// A button for sorting all hives
public class SortButton extends DirectoryButtons {

    // EFFECTS: Constructor for AddButton
    public SortButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.sortHives());
    }
}
