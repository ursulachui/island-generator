package elevation;

import JTSconversions.CentroidConvertor;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;

public class CalculateDistance {
    //calculates distance from center

    public double calcDistance(Structs.Vertex center, Structs.Vertex vertex){
        // create a geometry factory
        GeometryFactory geoFactory = new GeometryFactory();

        Coordinate v1 = new CentroidConvertor().convertToJTS(center); //centroid vertex
        Coordinate v2 = new CentroidConvertor().convertToJTS(vertex); //vertex we want to calculate distance from

        Point point_v1 = geoFactory.createPoint(v1);
        Point point_v2 = geoFactory.createPoint(v2);

        if (center.equals(null) || vertex.equals(null)) {
            throw new NullPointerException("Null values for center or vertex");
        }

        // calculate the distance between the points
        double distance = DistanceOp.distance(point_v1, point_v2);

        if (center == null || vertex == null) {
            throw new NullPointerException("No values for center or vertex");
        }

        return distance;
    }
}
