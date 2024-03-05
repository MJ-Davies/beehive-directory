package persistence;

import model.Hives;

import org.json.JSONObject;

import java.io.*;

// Writes the writable objects from the program's state to json file destination
// Modeled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonWriter.java
public class JsonWriter {
    private static final int TAB = 4; // 4 is used to abide with checkstyle indentation of 4 whitespaces
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Constructor for JsonWriter, sets destination to the given fileLocation
    public JsonWriter(String fileLocation) {
        this.destination = fileLocation;
    }

    // MODIFIES: this
    // EFFECTS: Creates PrintWriter to handle representations of objects into readable text
    //          if file destination does not exist, throw FileNotFoundException
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: Writes hives represented as a Json to the file.
    public void write(Hives wr) {
        JSONObject json = wr.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: Closes the PrintWriter
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: Writes the Json string to the file at the designated destination
    private void saveToFile(String json) {
        writer.print(json);
    }
}
