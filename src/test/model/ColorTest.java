package model;

import model.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    Color col;

    @BeforeEach
    public void setup() {
        col = Color.OTHER;
    }

    @Test
    public void colorToString() {
        assertEquals("other", Color.OTHER.colorToString());
    }
}
