package ui.graphics;

import model.Hive;
import model.Hives;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.function.Function;

// The metrics frame to display the metrics of each value in hives
// Some methods have been modeled from https://stackoverflow.com/questions/26420428/how-to-word-wrap-text-in-jlabel
public class MetricsFrame extends JFrame implements Frames {
    private static final int NUM_OF_COLUMNS = 4;
    private static final int COLUMN_WIDTH = 185;
    private static final int HEADER_HEIGHT = 30;
    private static final int WIDTH = COLUMN_WIDTH * NUM_OF_COLUMNS;
    private static final int HEIGHT = 500;
    private Hives hives;

    // EFFECTS: Constructor for MetricsFrame
    public MetricsFrame(Hives hives) {
        this.hives = hives;
        setFrameSpecs();
        addColumns();
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
        this.setSize(WIDTH, HEIGHT);
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

    // EFFECTS: Adds the metrics onto the frame
    public void addColumns() {
        addColumn("Location", Hive::getLocation, 0);
        addColumn("Color", Hive::getColorInString, COLUMN_WIDTH);
        addColumn("Primary Pollen Source", Hive::getPrimaryPollen, COLUMN_WIDTH * 2);
        addColumn("Secondary Pollen Source", Hive::getSecondaryPollen, COLUMN_WIDTH * 3);
    }

    // REQUIRES: this JFrame layout is set to null (absolute positioning)
    // MODIFIES: this
    // EFFECTS: Adds a column to this frame, metric is provided with the inputted header and getter method
    // Modeled from https://stackoverflow.com/questions/26420428/how-to-word-wrap-text-in-jlabel
    public void addColumn(String headerName, Function<Hive, String> getFunc, int posX) {
        JPanel column = new JPanel();
        column.setLayout(null);
        column.setBounds(posX, 0, COLUMN_WIDTH, HEIGHT - 37);

        JPanel header = new JPanel();
        formatHeader(header, headerName);

        JTextArea metricText = new JTextArea();
        metricText.setEditable(false);
        metricText.setText(getText(getFunc));
        metricText.setLineWrap(false);
        int height = (int) metricText.getPreferredScrollableViewportSize().getHeight();
        metricText.setPreferredSize(new Dimension((int) metricText.getPreferredScrollableViewportSize().getWidth(),
                height));
        metricText.setMinimumSize(new Dimension(COLUMN_WIDTH, height));
        metricText.setBackground(new Color(239, 237, 212));

        JPanel body = new JPanel();
        body.setBounds(0, HEADER_HEIGHT, COLUMN_WIDTH,  HEIGHT - 66);
        body.setLayout(new BorderLayout());

        JScrollPane scrollable = new JScrollPane(metricText);

        body.add(scrollable);
        column.add(body);
        column.add(header);
        this.add(column);
    }

    // REQUIRES: header is a BorderLayout (default layout)
    // MODIFIES: header
    // EFFECTS: Formats the header with appropriate specifications
    public void formatHeader(JPanel header, String name) {
        header.setBounds(0, 0, COLUMN_WIDTH, HEADER_HEIGHT);
        header.add(new JLabel(name));
        header.setBackground(Color.white);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
    }

    // EFFECTS: Returns a string for the metrics of a given field specified by the get method (higher order function)
    //          in the form of "value: frequency"
    public String getText(Function<Hive, String> getFunc) {
        LinkedList<String> uniqueX = hives.getDistinctStringX(getFunc);
        LinkedList<Number> frequencyOfX = hives.getFrequencyOfX(getFunc, uniqueX);

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < uniqueX.size(); i++) {
            String line = uniqueX.get(i) + ": " + frequencyOfX.get(i) + "\n";
            message.append(line);
        }
        return message + "";
    }

    // getter methods:
    public Hives getHives() {
        return this.hives;
    }
}
