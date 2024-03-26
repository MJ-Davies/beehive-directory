package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for removing a hive
public class RemoveButton extends DirectoryButton {
    private String nameOfHive;

    // EFFECTS: Constructor for RemoveButton, removes this hive from the directory
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
