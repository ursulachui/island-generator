package populate;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import org.locationtech.jts.geom.Coordinate;
import polygonType.*;
import shape.LagoonCircle;

import java.util.List;

public class MVP implements Populator{

    @Override
    public Structs.Mesh populate(Configuration config, Structs.Mesh mesh, List<Coordinate> polygons, List<Integer> inIslandIndices, List<Integer> inOceanIndices) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(mesh.getVerticesList());
        clone.addAllSegments(mesh.getSegmentsList());

        //identifying lagoon and beach polygons which is only for the MVP
        List<Integer> inLagoonIndices = new LagoonCircle().setShape(polygons);

        //generating inBeachIndices, which is island indices - lagoon indices
        List<Integer> inBeachIndices = new LagoonCircle().removeShape(inIslandIndices, inLagoonIndices);

        List<Structs.Polygon> meshPolygons = mesh.getPolygonsList();

        List<Structs.Polygon> inIslandPolygons = new LandPolygons().make(meshPolygons, inIslandIndices); //this returns a LIST of polygons with properties. the actual mesh polygons proprties havent been updated..?
        List<Structs.Polygon> inLagoonPolygons = new LagoonPolygons().make(inIslandPolygons, inLagoonIndices);
        List<Structs.Polygon> inOceanPolygons = new OceanPolygons().make(inLagoonPolygons, inOceanIndices);
        List<Structs.Polygon> inBeachPolygons = new BeachPolygon().make(inOceanPolygons, inBeachIndices);

        clone.addAllPolygons(inBeachPolygons);

        Structs.Mesh cloneMesh = clone.build();

        return cloneMesh;
    }
}
