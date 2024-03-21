package persistence;

import model.Color;
import model.Hive;
import model.Hives;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.LinkedList;

// Modeled from
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java
public class JsonWriterTest extends JsonTest {
    Hives hives;

    @BeforeEach
    void setup() {
        hives = new Hives();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Runs as expected
        }
    }

    @Test
    void testWriterEmptyHives() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyHives.json");
            writer.open();
            writer.write(hives);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyHives.json");
            hives = reader.read();
            assertEquals(0, hives.getListOfHives().size());
        } catch (IOException e) {
            fail("Unexpected IOException was thrown.");
        }
    }

    @Test
    void testWriterGeneralHives() {
        try {
            hives.addHive("Name1", "Location1");
            hives.addHive("Name2", "Location2");
            editFields(hives.getListOfHives());
            JsonWriter writer = new JsonWriter("./data/testWriteGeneralHives.json");
            writer.open();
            writer.write(hives);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteGeneralHives.json");
            hives = reader.read();
            List<Hive> listOfHives = hives.getListOfHives();
            assertEquals(2, listOfHives.size());
            checkHive(listOfHives.get(0), "Name1", "Location1", Color.GOLDEN, "Flower1",
                    "Unspecified", "This is a note");
            checkHive(listOfHives.get(1), "Name2", "Location2", Color.OTHER, "Flower2",
                    "Flower3", "Unspecified");
        } catch (IOException e) {
            fail("Unexpected IOException was thrown.");
        }
    }

    // EFFECTS: Helper function to edit fields set by default to be custom.
    public void editFields(LinkedList<Hive> hiveList) {
        Hive hive1 = hiveList.get(0); // has custom color, primary pollen, and notes
        Hive hive2 = hiveList.get(1); // has custom primary and secondary pollen
        hive1.setColor("golden");
        hive1.setPrimaryPollenSource("Flower1");
        hive1.setNotes("This is a note");
        hive2.setPrimaryPollenSource("Flower2");
        hive2.setSecondaryPollenSource("Flower3");
    }
}
