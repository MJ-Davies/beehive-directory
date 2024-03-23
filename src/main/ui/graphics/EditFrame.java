package ui.graphics;

import model.Hive;
import ui.graphics.fields.*;

import javax.swing.*;
import java.awt.*;

// The editing frame for editing a hive
public class EditFrame extends JFrame implements Frames {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 550;
    private Hive hive;
    private DirectoryFrame directory;

    // EFFECTS: Constructor for EditFrame
    public EditFrame(Hive hive, DirectoryFrame directory) {
        this.hive = hive;
        this.directory = directory;
        setFrameSpecs();
        editingInterfaceSetup();
        setImageIcon();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame specifications
    public void setFrameSpecs() {
        this.setTitle("Beehive Directory - Editing: " + hive.getName()); // creates the title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the application
        this.setResizable(false); // prevents user from resizing the frame
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT); // sets the x and y dimensions of the frame
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes only metrics when selected
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
    // EFFECTS: Initializes the elements/panels of this frame
    public void editingInterfaceSetup() {
        editingInterfaceHeader();
        editingInterfaceBody();
    }

    // MODIFIES: this
    // EFFECTS: Creates the editing interface header with title
    public void editingInterfaceHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.white);
        header.setBounds(0, 0, WIDTH, 60);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 5));

        JLabel title = new JLabel("Editing " + hive.getName());
        title.setFont(new Font(null, Font.BOLD, 20));
        header.add(title);

        this.add(header);
    }

    // MODIFIES: this
    // EFFECTS: Creates the editing interface body with all text fields and buttons
    public void editingInterfaceBody() {
        JPanel body = new JPanel();
        body.setBackground(new Color(239, 237, 212));
        body.setBounds(0,60, WIDTH, HEIGHT - 60);
        body.setLayout(null);
        addFields(body);

        this.add(body);
    }

    // REQUIRES: body has a layout of null (absolute positioning)
    // MODIFIES: body
    // EFFECTS: Adds text fields and buttons for this frame
    public void addFields(JPanel body) {
        HiveFields name = new NamePanel(0, 0, WIDTH, 50, this.hive, this.directory);
        HiveFields location = new LocationPanel(0, 50, WIDTH, 50, this.hive, this.directory);
        HiveFields color = new ColorPanel(0, 100, WIDTH, 50, this.hive, this.directory);
        HiveFields primaryPollen = new PrimaryPollenPanel(0, 150, WIDTH, 50, this.hive,
                this.directory);
        HiveFields secondaryPollen = new SecondaryPollenPanel(0, 200, WIDTH, 50, this.hive,
                this.directory);
        HiveFields notes = new NotesPanel(0, 250, WIDTH, 50, this.hive, this.directory);
        body.add(name);
        body.add(location);
        body.add(color);
        body.add(primaryPollen);
        body.add(secondaryPollen);
        body.add(notes);
    }
}
