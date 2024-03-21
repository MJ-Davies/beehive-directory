package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for collecting the metrics of hives
public class MetricsButton extends MyButton {

    // EFFECTS: Constructor for MetricsButton
    public MetricsButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.getMetrics());
    }
}
