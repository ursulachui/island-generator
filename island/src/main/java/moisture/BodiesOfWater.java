package moisture;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;

import java.util.List;

public interface BodiesOfWater {
    public List<Structs.Polygon> generate(List<Structs.Polygon> elevatedPolys, int num);
}
