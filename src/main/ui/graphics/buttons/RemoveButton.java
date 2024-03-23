package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// A button for removing a hive
public class RemoveButton extends DirectoryButtons {
    String nameOfHive;

    // EFFECTS: Constructor for RemoveButton
    public RemoveButton(String text, DirectoryFrame frame, String name) {
        super(text, frame);
        this.nameOfHive = name;
        this.addActionListener(e -> frame.removeHive(nameOfHive));

    }

    // getter methods:
    public String getNameOfHive() {
        return this.nameOfHive;
    }
}
