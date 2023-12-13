package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Optional;


public class DistanceProperty implements PropertyAccess<String> {
    @Override
    public Optional<String> extract(List<Structs.Property> props) {
        String value = new Reader(props).get("distance");
        if (value == null)
            return Optional.empty();
        return Optional.of(value);
    }
    @Override
    public Optional<String> set(List<Structs.Property> props, String key, String value){
        new Reader(props).set(key, value);
        return null;
    }
}

