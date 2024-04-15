package persistence;

import org.json.JSONObject;

// Represents classes which can be written with Json
public interface Writable {
    // EFFECTS: Converts this object into a readable Json Object
    JSONObject toJson();
}
