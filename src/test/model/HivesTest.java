package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class HivesTest {
    Hives mainHive;
    LinkedList<Hive> mainListOfHives;
    Hives emptyHive;
    LinkedList<Hive> emptyListOfHives;

    Hive keke;
    Hive loco;
    Hive mani;

    @BeforeEach
    public void setup() {
        this.mainHive = new Hives();
        this.mainListOfHives = mainHive.getListOfHives();
        this.emptyHive = new Hives();
        this.emptyListOfHives = emptyHive.getListOfHives();

        mainHive.addHive("Keke", "BC");
        mainHive.addHive("Loco", "Alberta");
        mainHive.addHive("Mani", "BC");

        this.keke = mainListOfHives.get(0);
        this.loco = mainListOfHives.get(1);
        this.mani = mainListOfHives.get(2);

        customizeHiveFields();
    }

    public void customizeHiveFields() {
        Hive keke = mainListOfHives.get(0);
        Hive loco = mainListOfHives.get(1);
        Hive mani = mainListOfHives.get(2);

        keke.setPrimaryPollenSource("Wild flower");
        keke.setNotes("The first hive to exist :)");
        loco.setPrimaryPollenSource("Sunflower");
        loco.setSecondaryPollenSource("Lavender");
        loco.setColor("golden");
        mani.setPrimaryPollenSource("Lavender");
        mani.setSecondaryPollenSource("Wild flower");
    }

    @Test
    public void testConstructor() {
        Hives testHive = new Hives();
        assertEquals(0, testHive.getListOfHives().size());
    }

    @Test
    public void addHiveWithMultiple() {
        // test addHive to a hive with some hives
        mainHive.addHive("Lola", "US");
        Hive hiveAdded = mainListOfHives.getLast();

        assertEquals(4, mainListOfHives.size());
        assertEquals(hiveAdded.getName(), "Lola");
        assertEquals(hiveAdded.getLocation(), "US");
    }

    @Test
    public void addHiveToEmpty() {
        emptyHive.addHive("First", "Canada");
        Hive hiveAdded = emptyListOfHives.getLast();

        assertEquals(1, emptyListOfHives.size());
        assertEquals(hiveAdded.getName(), "First");
        assertEquals(hiveAdded.getLocation(), "Canada");
    }

    @Test
    public void testRemoveHive() {
        assertEquals(3, mainListOfHives.size());
        mainHive.removeHive("Loco");
        assertEquals(2, mainListOfHives.size());
    }

    @Test
    public void getPositionInHiveExists() {
        assertEquals(2, mainHive.getPositionInHives("Mani"));
        assertEquals(0, mainHive.getPositionInHives("Keke"));
    }

    @Test
    public void getPositionInHiveNotExist() {
        assertEquals(-1, mainHive.getPositionInHives("DNE"));
        assertEquals(-1, emptyHive.getPositionInHives("Keke"));
    }

    @Test
    public void testHiveExists() {
        assertTrue(mainHive.hiveExists("Loco"));
        assertFalse(mainHive.hiveExists("Pizza"));
        assertFalse(emptyHive.hiveExists("Poyo"));
    }

    @Test
    public void returnAllHiveNames() {
        assertEquals("There are no hives in this directory.", emptyHive.returnAllHiveNames());
        assertEquals("Hive names: Keke, Loco, Mani", mainHive.returnAllHiveNames());
    }

    @Test
    public void locationMetrics() {
        String message = "LOCATIONS: \n================\n"
                + "BC: 2\n"
                + "Alberta: 1\n";
        assertEquals(message, mainHive.getMetricX("LOCATIONS", Hive::getLocation));
    }

    @Test
    public void metricsWithoutHives() {
        assertEquals("No hives to obtain metrics from.", emptyHive.returnMetrics());
    }

    @Test
    public void metricsWithHives() {
        String message = mainHive.getMetricX("LOCATIONS", Hive::getLocation)
                + mainHive.getMetricX("COLORS", Hive::getColorInString)
                + mainHive.getMetricX("PRIMARY POLLENS", Hive::getPrimaryPollen)
                + mainHive.getMetricX("SECONDARY POLLENS", Hive::getSecondaryPollen);
        assertEquals(message, mainHive.returnMetrics());
    }

    @Test
    public void sortByAFlower() {
        mainHive.sortByPollen("Wild flower");
        mainListOfHives = mainHive.getListOfHives();
        assertEquals(keke, mainListOfHives.get(0));
        assertEquals(mani, mainListOfHives.get(1));
        assertEquals(loco, mainListOfHives.get(2));

        mainHive.sortByPollen("Lavender");
        mainListOfHives = mainHive.getListOfHives();
        assertEquals(mani, mainListOfHives.get(0));
        assertEquals(loco, mainListOfHives.get(1));
        assertEquals(keke, mainListOfHives.get(2));
    }

    @Test
    public void testGetDistinctLocation() {
        LinkedList<String> locations = mainHive.getDistinctStringX(Hive::getLocation);
        assertEquals("BC", locations.get(0));
        assertEquals("Alberta", locations.get(1));
    }

    @Test
    public void testGetDistinctPrimaryPollen() {
        LinkedList<String> locations = mainHive.getDistinctStringX(Hive::getPrimaryPollen);
        assertEquals("Wild flower", locations.get(0));
        assertEquals("Sunflower", locations.get(1));
        assertEquals("Lavender", locations.get(2));
    }

    @Test
    public void testGetFrequencyOfDistinctLocations() {
        LinkedList<String> locations = mainHive.getDistinctStringX(Hive::getLocation);
        LinkedList<Number> freq = mainHive.getFrequencyOfX(Hive::getLocation, locations);
        assertEquals(2, freq.get(0));
        assertEquals(1, freq.get(1));
    }

    @Test
    public void testViewPollensWithHives() {
        String message = "Keke: Wild flower, Unspecified\n"
                + "Loco: Sunflower, Lavender\n"
                + "Mani: Lavender, Wild flower\n";
        assertEquals(message, mainHive.viewPollens());
    }

    @Test
    public void testViewPollensWithoutHives() {
        assertEquals("No hives to view pollen.", emptyHive.viewPollens());
    }
}
