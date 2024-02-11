package ui;

import model.Hives;
import ui.Directory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDirectory {
    Directory mainDirectory;

    @BeforeEach
    public void setup() {
        this.mainDirectory = new Directory();
    }

    @Test
    public void testConstructor() {
        Directory direc = new Directory();
        assertEquals(0, direc.getHives().getListOfHives().size());
    }
}