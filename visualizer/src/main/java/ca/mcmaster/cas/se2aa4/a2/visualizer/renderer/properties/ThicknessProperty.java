package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ThicknessProperty implements PropertyAccess{
    @Override
    public Optional extract(List props) {
        String value = new Reader(props).get("thickness");
        if (value == null)
            return Optional.empty();
        return Optional.of(Integer.parseInt(value));
    }
}
