package ui;

import ui.EditInterface;
import model.Hive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditInterfaceTest {
    private EditInterface ei;
    private Hive thisHive;

    @BeforeEach
    public void setup() {
        thisHive = new Hive("New", "A location");
        ei = new EditInterface(thisHive);
    }

    @Test
    public void testConstructor() {
        assertEquals(thisHive, ei.getHive());
    }
}
