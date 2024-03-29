package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {
    private Map<String, String> contents;

    public Reader(List<Structs.Property> props) {
        this.contents = new HashMap<>();
        for(Structs.Property p: props){
            this.contents.put(p.getKey(), p.getValue());
        }
    }

    public String get(String key) {
        return this.contents.get(key);
    }

    public void set(String key, String value) { contents.put(key, value); }
}
