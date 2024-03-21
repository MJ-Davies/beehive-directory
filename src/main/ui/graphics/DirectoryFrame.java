package ui.graphics;

import model.Hives;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphics.buttons.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// The main frame for the directory
// Code was modeled from https://youtu.be/Kmgo00avvEw?si=j-behtRv18UqwODt
// Some method were modeled from (related methods include this citation under their specifications):
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
public class DirectoryFrame extends JFrame {
    private static final String JSON_FILE_DESTINATION = "./data/hives.json";
    private static final int WIDTH = 750;
    private static final int HEIGHT = 500;
    private JButton firstButton;
    private Hives hives;
    private JsonReader reader;
    private JsonWriter writer;


    // EFFECTS: Constructor for DirectoryFrame
    public DirectoryFrame() {
        setFrameSpecs();
        goToMainScreen();
        setImageIcon();
        this.setVisible(true); // makes the frame visible
        hives = new Hives();
        this.writer = new JsonWriter(JSON_FILE_DESTINATION);
        this.reader = new JsonReader(JSON_FILE_DESTINATION);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame specifications
    public void setFrameSpecs() {
        this.setTitle("Beehive Directory"); // creates the title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
        this.setResizable(false); // prevents user from resizing the frame
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT); // sets the x and y dimensions of the frame
        this.getContentPane().setBackground(new Color(239, 237, 212)); // sets frame background
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
    // EFFECTS: Creates the main directory screen
    public void goToMainScreen() {
        mainScreenHeader();
    }

    // MODIFIES: this
    // EFFECTS: Creates the header for the main directory screen
    public void mainScreenHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.white);
        header.setBounds(0, 0, WIDTH, 60);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));
        addHeaderButtons(header);
        this.add(header);
    }

    // MODIFIES: this
    // EFFECTS: Adds header buttons to this frame
    public void addHeaderButtons(JPanel header) {
        MyButton add = new AddButton("Add", this);
        MyButton metrics = new MetricsButton("Metrics", this);
//        MyButton sort = new SortButton(); !!!
        MyButton save = new SaveButton("Save", this);
        MyButton load = new LoadButton("Load", this);
        header.add(add);
        header.add(metrics);
        //header.add(sort);
        header.add(save);
        header.add(load);
    }

    // MODIFIES: this
    // EFFECTS: Creates the edit screen
    public void goToEditScreen() {
        // stub
    }

    // Delete later !!!
    public void createJButton() {
        firstButton = new JButton();
        firstButton.setBounds(0, 0, 100, 100);
        firstButton.addActionListener(e -> System.out.println("Test"));
        firstButton.setText("This is a test");
        firstButton.setFocusable(false);
        this.add(firstButton);
    }

    // Functionality methods
    // EFFECTS: Prompts user for name and location and successfully adds the hive
    public void addHive() {
        String name = JOptionPane.showInputDialog("Enter a name for the hive:");
        if (name != null) {
            String location = JOptionPane.showInputDialog("Enter a location for the hive:");
            if (location != null) {
                hives.addHive(name, location);
            }
        }
    }

    // EFFECTS: Produces a new window for the metrics display
    public void getMetrics() {
        new MetricsFrame(hives);
    }

    // EFFECTS: Saves the hives in this directory to file destination
    // Modelled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    public void saveHives() {
        try {
            writer.open();
            writer.write(hives);
            writer.close();
            JOptionPane.showMessageDialog(null,
                    "Saved hives to " + JSON_FILE_DESTINATION,
                    "Saved Successfully", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_FILE_DESTINATION,
                    "Save Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads hives from file destination
    // Modelled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    public void loadHives() {
        try {
            hives = reader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded hives from " + JSON_FILE_DESTINATION,
                    "Loaded Successfully", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_FILE_DESTINATION,
                    "Load Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    // getter methods
    public Hives getHives() {
        return this.hives;
    }
}
