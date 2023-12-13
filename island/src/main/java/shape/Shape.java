package shape;

import java.util.*;

import configuration.Configuration;
import org.locationtech.jts.geom.*;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public interface Shape {
    Structs.Mesh build(Configuration config);
    List setShape(List<Coordinate> list);
    List remainingWater(List<Coordinate> polygons);
    Polygon createPerimeter();
}
