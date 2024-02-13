package model;

import model.Hive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class HiveTest {
    Hive mainHive;

    @BeforeEach
    public void setup() {
        mainHive = new Hive("Main", "Mainland");
    }

    @Test
    public void testConstructor() {
        Hive newHive = new Hive("New", "Newfoundland");
        LinkedList<String> fields = new LinkedList<String>();
        fields.add("name");
        fields.add("location");
        fields.add("color");
        fields.add("primary pollen source");
        fields.add("secondary pollen source");
        fields.add("notes");

        assertEquals("New", newHive.getName());
        assertEquals("Newfoundland", newHive.getLocation());
        assertEquals(Color.OTHER, newHive.getColor());
        assertEquals("Unspecified", newHive.getPrimaryPollen());
        assertEquals("Unspecified", newHive.getSecondaryPollen());
        assertEquals("Unspecified", newHive.getNotes());

        for (int i = 0; i < newHive.getAvailableFields().size(); i++) {
            assertEquals(fields.get(i), newHive.getAvailableFields().get(i));
        }
    }

    @Test
    public void testReturnAllFieldValues() {
        String message = "Name: " + mainHive.getName()
                + "\n Location: " + mainHive.getLocation()
                + "\n Color [golden, amber, light, dark, other]: " + mainHive.getColorInString()
                + "\n Primary Pollen Source: " + mainHive.getPrimaryPollen()
                + "\n Secondary Pollen Source: " + mainHive.getSecondaryPollen()
                + "\n Notes: " + mainHive.getNotes();
        assertEquals(message, mainHive.returnAllFieldValues());
    }

    @Test
    public void testSetName() {
        mainHive.setName("New name");
        assertEquals("New name", mainHive.getName());
    }

    @Test
    public void testSetLocation() {
        mainHive.setLocation("Germany");
        assertEquals("Germany", mainHive.getLocation());
    }

    @Test
    public void testSetColor() {
        mainHive.setColor("golden");
        assertEquals(Color.GOLDEN, mainHive.getColor());
        mainHive.setColor("amber");
        assertEquals(Color.AMBER, mainHive.getColor());
        mainHive.setColor("light");
        assertEquals(Color.LIGHT, mainHive.getColor());
        mainHive.setColor("dark");
        assertEquals(Color.DARK, mainHive.getColor());
        mainHive.setColor("other");
        assertEquals(Color.OTHER, mainHive.getColor());
        mainHive.setColor("green");
        assertEquals(Color.OTHER, mainHive.getColor());
    }

    @Test
    public void testGetColorInString() {
        mainHive.setColor("golden");
        assertEquals("golden", mainHive.getColorInString());
    }
}
