package persistence;

import model.Hive;
import model.Color;

import static org.junit.jupiter.api.Assertions.*;

// Modeled from
// github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java
public class JsonTest {
    // Checks to see whether the actual fields of the hive matches the expected fields
    protected void checkHive(Hive hive, String name, String location, Color color, String primaryPollen,
                             String secondaryPollen, String notes) {
        assertEquals(name, hive.getName());
        assertEquals(location, hive.getLocation());
        assertEquals(color, hive.getColor());
        assertEquals(primaryPollen, hive.getPrimaryPollen());
        assertEquals(secondaryPollen, hive.getSecondaryPollen());
        assertEquals(notes, hive.getNotes());
    }
}

