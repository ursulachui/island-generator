package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public interface ElevationProfile {
    public List<Structs.Polygon> make(List<Structs.Polygon> meshPolygons, Structs.Mesh mesh, Structs.Vertex center);
}
