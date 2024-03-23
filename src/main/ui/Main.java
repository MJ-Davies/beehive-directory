package ui;

import model.Hive; // !!! Delete later
import ui.graphics.DirectoryFrame;
import ui.graphics.EditFrame; // !!! Delete later

// Main program, initiates a Directory
public class Main {
    public static void main(String[] args) {
        new DirectoryFrame(); //!!! Uncomment later
        //new EditFrame(new Hive("My name", "My location"), null); // !!! Delete Later
        //Directory mainDirectory = new Directory(frame);
        //mainDirectory.runDirectory();
    }
}
