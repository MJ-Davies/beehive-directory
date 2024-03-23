package ui.graphics;

import model.Hives;

import javax.swing.*;
import java.awt.*;

// Class for the metrics frame to display the metrics of each value in hives
public class MetricsFrame extends JFrame implements Frames {
    private Hives hives;

    // EFFECTS: Constructor for MetricsFrame
    public MetricsFrame(Hives hives) {
        this.hives = hives;
        setFrameSpecs();
        addText();
        this.setVisible(true); // makes the frame visible
        setImageIcon();
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame specifications
    public void setFrameSpecs() {
        this.setTitle("Beehive Directory - Metrics");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(null);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: Sets the image icon of the frame
    public void setImageIcon() {
        // Logo was taken from WikiMedia under Creative Commons license:
        // https://commons.wikimedia.org/wiki/File:OpenMoji-black_1F41D.svg
        ImageIcon image = new ImageIcon("./src/main/ui/graphics/images/logo.png"); // creates an icon
        this.setIconImage(image.getImage()); // changes icon of the frame
    }

    // MODIFIES: this
    // EFFECTS: Adds the metrics onto the frame
    public void addText() {
        JTextArea label = new JTextArea();
        label.setBounds(0, 0, 500, 500);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        label.setLineWrap(true);
        label.setEditable(false);

        String message = hives.returnMetrics();
        label.setText(message);
        this.add(label);
    }

    // getter methods:
    public Hives getHives() {
        return this.hives;
    }
}
