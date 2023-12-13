package soil;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public interface SoilProfile {
    public List<Structs.Polygon> make(Structs.Mesh mesh, List<Structs.Polygon> polyList);
}

