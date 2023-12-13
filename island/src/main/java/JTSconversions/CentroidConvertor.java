package JTSconversions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.*;

public class CentroidConvertor{

    public List convertToJTS(Structs.Mesh aMesh){
        List<Coordinate> coordinates = new ArrayList<>();
        List<Structs.Polygon> polygons = aMesh.getPolygonsList();

        for (Structs.Polygon p : polygons){
            Integer centroid = p.getCentroidIdx();
            double X = aMesh.getVertices(centroid).getX();
            double Y = aMesh.getVertices(centroid).getY();

            Coordinate coord = new Coordinate(X, Y);
            coordinates.add(coord);
        }
        return coordinates;
    }

    public Coordinate convertToJTS(Structs.Vertex vertex){
        GeometryFactory geometryFactory = new GeometryFactory();
            double X = vertex.getX();
            double Y = vertex.getY();

            Coordinate coord = new Coordinate(X, Y);
            return coord;
    }
}
