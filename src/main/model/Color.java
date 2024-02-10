package model;

import java.util.LinkedList;

public enum Color {
    GOLDEN("golden"),
    AMBER("amber"),
    LIGHT("light"),
    DARK("dark"),
    OTHER("other");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String colorToString() {
        return this.name;
    }
}
