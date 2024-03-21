package persistence;

import model.Hive;
import model.Hives;
import model.Color;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

// Modeled from
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Runs as expected
        }
    }

    @Test
    void testReaderEmptyHives() {
        JsonReader reader = new JsonReader("./data/testEmptyHives.json");
        try {
            Hives hives = reader.read();
            assertEquals(0, hives.getListOfHives().size());
        } catch (IOException e) {
            fail("Couldn't read from file, unexpected IOException was caught.");
        }
    }

    @Test
    void testReaderGeneralHives() {
        JsonReader reader = new JsonReader("./data/testGeneralHives.json");
        try {
            Hives hives = reader.read();
            List<Hive> listOfHives = hives.getListOfHives();
            assertEquals(2, listOfHives.size());
            checkHive(listOfHives.get(0), "Open hive", "Home", Color.AMBER, "Sunflower",
                    "Poppy", "First hive");
            checkHive(listOfHives.get(1), "My hive", "Greenland", Color.OTHER, "Dandelion",
                    "Unspecified", "Inactive.");
        } catch (IOException e) {
            fail("Couldn't read from file, unexpected IOException was caught.");
        }
    }
}
