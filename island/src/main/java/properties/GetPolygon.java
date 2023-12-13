package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashMap;
import java.util.Map;

public class GetPolygon {
    Map<Integer, Structs.Polygon> centroidPoly = new HashMap();
    public GetPolygon(Structs.Mesh mesh){
        for(Structs.Polygon poly: mesh.getPolygonsList()){
            centroidPoly.put(poly.getCentroidIdx(), poly);
        }
    }

    public Structs.Polygon getPoly(int centroid){
        return centroidPoly.get(centroid);
    }
}