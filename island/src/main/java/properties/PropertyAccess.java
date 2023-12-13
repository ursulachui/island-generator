package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Optional;

public interface PropertyAccess<T> {
    Optional<T> extract(List<Structs.Property> props);
    Optional<T> set(List<Structs.Property> props, String key, String value);

}

