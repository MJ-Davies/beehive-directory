package model;

import java.util.LinkedList;

// Represents the distinct values associated with the color of honey
public enum Color {
    GOLDEN("golden"),
    AMBER("amber"),
    LIGHT("light"),
    DARK("dark"),
    OTHER("other");

    private final String name;

    // EFFECTS: Constructor for the Color enumerator
    Color(String name) {
        this.name = name;
    }

    // EFFECTS: Returns the string associated with the enumerator
    public String colorToString() {
        return this.name;
    }
}
