package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;
import java.util.Optional;

public class ElevationProperty implements PropertyAccess<String> {
    @Override
    public Optional<String> extract(List<Property> props) {
        String value = new Reader(props).get("elevation");
        if (value == null)
            return Optional.empty();
        return Optional.of(value);
    }
    @Override
    public Optional<String> set(List<Property> props, String key, String value){
        new Reader(props).set(key, value);
        return null;
    }
}
