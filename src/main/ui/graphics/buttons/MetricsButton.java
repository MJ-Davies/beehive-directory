package ui.graphics.buttons;

import ui.graphics.DirectoryFrame;

// Button for collecting the metrics of hives
public class MetricsButton extends DirectoryButton {
    // EFFECTS: Constructor for MetricsButton, creates a metrics frame upon pressing
    public MetricsButton(String text, DirectoryFrame frame) {
        super(text, frame);
        this.addActionListener(e -> frame.getMetrics());
    }
}
