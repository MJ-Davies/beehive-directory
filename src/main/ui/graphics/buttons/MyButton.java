package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.*;

// Abstract class for all buttons in this program
public abstract class MyButton extends JButton {
    protected DirectoryFrame frame;

    // EFFECTS: Constructor for MyButton
    public MyButton(String text, DirectoryFrame frame) {
        this.setText(text);
        this.setFocusable(false);
        this.frame = frame;
        stylizeButton();
    }

    // MODIFIES: this
    // EFFECTS: Stylizes the buttons
    public void stylizeButton() {
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setBackground(Color.lightGray);
    }
}
