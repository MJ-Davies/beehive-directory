package persistence;

import org.json.JSONObject;

// Represents classes which can be written with Json
// Modelled from github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java
public interface Writable {
    // EFFECTS: Converts this object into a readable Json Object
    JSONObject toJson();
}
