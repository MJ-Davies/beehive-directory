package persistence;

import model.Hives;
import model.Hive;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Reads Json file from file destination and converts it to an object accessible by Java
// Modeled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
public class JsonReader {
    private String relativeSourcePath;

    // REQUIRES: destination is a relative path
    // EFFECTS: Constructor for JsonReader, sets the source to the given destination
    public JsonReader(String destination) {
        this.relativeSourcePath = destination;
    }

    // EFFECTS: reads the hives from the file and returns it;
    //          if an error occurs in reading data, throw an IOException
    public Hives read() throws IOException {
        String jsonData = readFile(relativeSourcePath);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHives(jsonObject);
    }

    // EFFECTS: Reads source file and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Hives parseHives(JSONObject jsonObject) {
        Hives h = new Hives();
        addHives(h, jsonObject);
        return h;
    }

    // MODIFIES: hives
    // EFFECTS: parses individual hives from JSON object and adds them to hives
    private void addHives(Hives hives, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("hives");
        for (Object json : jsonArray) {
            JSONObject nextHive = (JSONObject) json;
            addHive(hives, nextHive);
        }
    }

    // MODIFIES: hives
    // EFFECTS: parses hive from JSON object and adds it to hives
    private void addHive(Hives hives, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String location = jsonObject.getString("location");
        Hive existingHive = new Hive(name, location);
        addExistingFields(existingHive, jsonObject);
        hives.addExistingHive(existingHive);
    }

    // EFFECTS: parses hive from JSON object and sets the corresponding fields to the hive
    private void addExistingFields(Hive hive, JSONObject jsonObject) {
        String color = jsonObject.getString("color");
        String primaryPollen = jsonObject.getString("primaryPollenSource");
        String secondaryPollen = jsonObject.getString("secondaryPollenSource");
        String notes = jsonObject.getString("notes");
        hive.setColor(color);
        hive.setPrimaryPollenSource(primaryPollen);
        hive.setSecondaryPollenSource(secondaryPollen);
        hive.setNotes(notes);
    }
}
