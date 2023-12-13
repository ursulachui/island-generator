package urbanism;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public interface City {
    public List<Structs.Polygon> graphConversion(Structs.Mesh mesh, List<Structs.Polygon> graphPolys, int numCities);
}
