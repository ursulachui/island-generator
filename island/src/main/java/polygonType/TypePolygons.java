package polygonType;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.lang.reflect.Type;
import java.util.List;

public interface TypePolygons<T> {
    public List<Structs.Polygon> make(List<Structs.Polygon> polyList, List<T> inSomething);
}
