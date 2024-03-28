package ui.graphics.window;

import model.Event;
import model.EventLog;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

// Custom actions to do upon window modifications
// Modeled from
// https://stackoverflow.com/questions/20304329/how-to-save-before-exit-java
public class DirectoryWindow extends WindowAdapter {
    private DirectoryFrame directory;

    // EFFECTS: Constructor for DirectoryWindow
    public DirectoryWindow(DirectoryFrame directory) {
        this.directory = directory;
    }

    // EFFECTS: Prompts the user to save before closing, after receiving an input, exits the program
    @Override
    public void windowClosing(WindowEvent we) {
        int answer = JOptionPane.showOptionDialog(
                null,
                "Do you want to save before quitting?",
                "Save Before Quitting",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                0);
        if (answer == 0) {
            directory.saveHives();
        }
        printEvents();
        System.exit(0);
    }

    // EFFECTS: Prints all the events that have been logged from EventLog
    public void printEvents() {
        Iterator<Event> events = EventLog.getInstance().iterator();
        while (events.hasNext()) {
            System.out.println(events.next().getDescription());
        }
    }
}
