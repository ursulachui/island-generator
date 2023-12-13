package properties;

import java.util.List;
import java.util.Optional;

public class ThicknessProperty implements PropertyAccess{
    @Override
    public Optional extract(List props) {
        String value = new Reader(props).get("thickness");
        if (value == null)
            return Optional.empty();
        return Optional.of(value);
    }

    @Override
    public Optional set(List props, String key, String value) {
        new Reader(props).set(key, value);
        return null;
    }
}
