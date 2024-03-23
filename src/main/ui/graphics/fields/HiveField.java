package ui.graphics.fields;

import model.Hive;
import ui.graphics.DirectoryFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Abstract class for all fields in this program, meant to represent the visual component of the edit frame
public abstract class HiveField extends JPanel {
    protected static final String EMPTY_DEFAULT_MSG = "Unspecified";
    protected Hive hive;
    protected DirectoryFrame directory;
    private int width;
    private int height;

    // EFFECTS: Constructor for HiveFields
    public HiveField(int posX, int posY, int width, int height, Hive hive, DirectoryFrame directory) {
        this.hive = hive;
        this.width = width;
        this.height = height;
        this.directory = directory;
        this.setBounds(posX, posY, width, height);
        this.setBackground(Color.red);
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        addElements();
    }

    // MODIFIES: this
    // EFFECTS: Adds the necessary containers and J-Elements to this current field
    public void addElements() {
        JPanel leftContainer = new JPanel();
        leftContainer.setBounds(0, 0, width / 2, height - 1);
        leftContainer.setBackground(new Color(239, 237, 212));
        addFieldName(leftContainer);
        JPanel rightContainer = new JPanel();
        rightContainer.setBounds(width / 2, 0, width / 2, height - 1);
        rightContainer.setBackground(new Color(239, 237, 212));
        rightContainer.setLayout(null);
        addInputField(rightContainer);
        this.add(leftContainer);
        this.add(rightContainer);
    }

    // REQUIRES: container has FlowLayout (default layout)
    // MODIFIES: container
    // EFFECTS: Adds the appropriate field name as a JLabel to container
    public void addFieldName(JPanel container) {
        JLabel fieldName = new JLabel(getFieldName());
        fieldName.setFont(new Font(null, Font.PLAIN, 15));
        container.add(fieldName);
    }

    // EFFECTS: Abstract method for getting the appropriate field name
    public abstract String getFieldName();

    // REQUIRES: container layout is null (absolute positioning)
    // MODIFIES: container
    // EFFECTS: Adds the text field and confirm
    public void addInputField(JPanel container) {
        JTextField input = new JTextField(fieldValue());
        input.setBounds(0, 10, width / 4, 20);

        // Creates a confirm button. This was not created as a new button class as this is the only button which exists
        // in the edit frame
        JButton confirm = new JButton("Confirm");
        confirm.setBounds((width / 4) + 30, 10, 100, 30);
        confirm.setFocusable(false);
        confirm.addActionListener(e -> updateField(e, input));

        container.add(input);
        container.add(confirm);
    }

    // EFFECTS: Adds a message next to the field name that hive was edited successfully
    public void confirmMessage(String fieldName) {
        JOptionPane.showMessageDialog(null,
                fieldName + " was edited successfully!",
                "Successful Edit", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: Abstract method for getting the current field value
    public abstract String fieldValue();

    // MODIFIES: this
    // EFFECTS: Abstract method for updating the current state of the text field for the field
    public abstract void updateField(ActionEvent e, JTextField input);

    // getter methods:
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
