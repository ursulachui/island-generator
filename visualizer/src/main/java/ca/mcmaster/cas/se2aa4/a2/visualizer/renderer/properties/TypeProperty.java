package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;
import java.util.Optional;

public class TypeProperty implements PropertyAccess<String> {
    @Override
    public Optional<String> extract(List<Property> props) {
        String value = new Reader(props).get("type");
        if (value == null)
            return Optional.empty();
        return Optional.of(value);
    }

}
