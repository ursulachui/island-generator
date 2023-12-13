package biomes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public interface Biome {
    public List<Structs.Polygon> makeBiome(List<Structs.Polygon> absorbedPolygons);
}
