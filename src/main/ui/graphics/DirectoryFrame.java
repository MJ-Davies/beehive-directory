package ui.graphics;

import model.Hives;
import model.Hive;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.graphics.buttons.*;

import javax.swing.*;
import static javax.swing.ScrollPaneConstants.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// The main frame for the directory
// Code was modeled from https://youtu.be/Kmgo00avvEw?si=j-behtRv18UqwODt
// Some method were modeled from (related methods include this citation under their specifications):
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
public class DirectoryFrame extends JFrame {
    private static final String JSON_FILE_DESTINATION = "./data/hives.json";
    private static final int WIDTH = 900;
    private static final int HEIGHT = 650;
    private JPanel body;
    private Hives hives;
    private JsonReader reader;
    private JsonWriter writer;


    // EFFECTS: Constructor for DirectoryFrame
    public DirectoryFrame() {
        hives = new Hives();
        hives.addHive("Yeyte", "Ko"); // !!!
        setFrameSpecs();
        goToMainScreen();
        setImageIcon();
        this.setVisible(true); // makes the frame visible
        this.writer = new JsonWriter(JSON_FILE_DESTINATION);
        this.reader = new JsonReader(JSON_FILE_DESTINATION);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame specifications
    public void setFrameSpecs() {
        this.setTitle("Beehive Directory"); // creates the title
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // exit the application
        this.setResizable(false); // prevents user from resizing the frame
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT); // sets the x and y dimensions of the frame
        this.getContentPane().setBackground(new Color(239, 237, 212)); // sets frame background
        saveBeforeClosing();
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
    // EFFECTS: Sets the specification such that the user is prompted to save before closing
    // Modeled from:
    // https://stackoverflow.com/questions/20304329/how-to-save-before-exit-java
    public void saveBeforeClosing() {
        this.addWindowListener(new WindowAdapter() {
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
                    saveHives();
                }
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Creates the main directory screen
    public void goToMainScreen() {
        mainScreenHeader();
        mainScreenBody();
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
        MyButton sort = new SortButton("Sort", this);
        MyButton save = new SaveButton("Save", this);
        MyButton load = new LoadButton("Load", this);
        header.add(add);
        header.add(metrics);
        header.add(sort);
        header.add(save);
        header.add(load);
    }

    // MODIFIES: this
    // EFFECTS: Creates the body panel to display all hives for the main directory screen
    // Modeled from:
    // https://stackoverflow.com/questions/9393480/java-jpanel-inside-jscrollpane
    // https://stackoverflow.com/questions/2501861/how-can-i-remove-a-jpanel-from-a-jframe
    // https://stackoverflow.com/questions/1727840/disable-horizontal-scroll-in-jscrollpane
    public void mainScreenBody() {
        // this acts to update the main screen while not overlapping outdated old screens
        if (this.body != null) {
            this.remove(this.body);
        }
        this.body = new JPanel();
        body.setBounds(0, 60, WIDTH - 15, HEIGHT - 100); // - 15 to account for scrollbar
        // this body makes BorderLayout local to this section of the frame
        body.setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(new Color(239, 237, 212));
        int heightOfItems = addHiveListItems(content);
        content.setPreferredSize(new Dimension(WIDTH, heightOfItems));

        JScrollPane scrollable = new JScrollPane(content);
        scrollable.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        body.add(scrollable, BorderLayout.CENTER);
        this.add(body);
        this.setVisible(true);
    }

    // MODIFIES: body
    // EFFECTS: Displays all the hives in hives
    public int addHiveListItems(JPanel body) {
        int height = 0;
        for (Hive hive : hives.getListOfHives()) {
            JPanel hiveItem = new JPanel();
            hiveItem.setBackground(Color.white);
            hiveItem.setPreferredSize(new Dimension(WIDTH, 50));
            addHiveElements(hive, hiveItem);
            body.add(hiveItem);
            height += 50 + 5;
        }
        return height;
    }

    // MODIFIES: container
    // EFFECTS: Adds name, primary pollen, secondary pollen, remove button, and edit button for this hive element
    public void addHiveElements(Hive hive, JPanel container) {
        JPanel leftContainer = new JPanel();
        leftContainer.setPreferredSize(new Dimension(200, 50));
        leftContainer.setBackground(Color.white);

        JPanel rightContainer = new JPanel();
        rightContainer.setPreferredSize(new Dimension(200, 50));
        rightContainer.setBackground(Color.white);

        JLabel hiveName = new JLabel("Name: " + hive.getName());
        JLabel hivePollens = new JLabel("Pollens: " + hive.getPrimaryPollen() + "; " + hive.getSecondaryPollen());

        leftContainer.add(hiveName);
        leftContainer.add(hivePollens);

        rightContainer.add(new EditButton("Edit", this, hive));
        rightContainer.add(new RemoveButton("Remove", this, hive.getName()));

        container.add(leftContainer);
        container.add(rightContainer);
    }

    // MODIFIES: this
    // EFFECTS: Creates the edit screen
    public void goToEditScreen(Hive hive) {
        // stub
    }

    // Functionality methods:
    // MODIFIES: this
    // EFFECTS: Prompts user for name and location and successfully adds the hive
    public void addHive() {
        String name = JOptionPane.showInputDialog("Enter a name for the hive:");
        if (name != null) {
            String location = JOptionPane.showInputDialog("Enter a location for the hive:");
            if (location != null) {
                hives.addHive(name, location);
                mainScreenBody();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the selected hive
    public void removeHive(String name) {
        hives.removeHive(name);
        mainScreenBody();
        JOptionPane.showMessageDialog(null,
                name + " was removed successfully!",
                "Removed Successfully", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Produces a new window for the metrics display
    public void getMetrics() {
        new MetricsFrame(hives);
    }

    // MODIFIES: this
    // EFFECTS: Prompts the user to sort by what field and sorts hives according to that field
    // Modeled from:
    // https://stackoverflow.com/questions/1257420/making-a-joptionpane-with-4-options
    public void sortHives() {
        String[] options = new String[] {"pollen source"};
        int answer = JOptionPane.showOptionDialog(null, "How would you like to sort by?",
                "Sort Hives", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                options[0]);
        if (answer == 0) {
            String pollen = JOptionPane.showInputDialog("Enter the pollen type (case sensitive):");
            hives.sortByPollen(pollen);
            mainScreenBody();
            JOptionPane.showMessageDialog(null,
                    "Hives are now sorted by " + pollen + ".",
                    "Sorted Successfully", JOptionPane.INFORMATION_MESSAGE);
        }
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
            mainScreenBody();
            JOptionPane.showMessageDialog(null,
                    "Loaded hives from " + JSON_FILE_DESTINATION,
                    "Loaded Successfully", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_FILE_DESTINATION,
                    "Load Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    // getter methods:
    public Hives getHives() {
        return this.hives;
    }
}