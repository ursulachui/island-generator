package populate;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import org.locationtech.jts.geom.Coordinate;

import java.util.List;

public interface Populator {

    public Structs.Mesh populate(Configuration config, Structs.Mesh mesh, List<Coordinate> coords, List<Integer> island, List<Integer> ocean);
}
