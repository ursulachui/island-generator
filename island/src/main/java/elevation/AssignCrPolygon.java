package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.DistanceProperty;

import java.util.Set;

public class AssignCrPolygon extends AssignPolygon {

    //associates a polygon with ONE elevation level, based on the average of its vertices
    //adds the colour property, with a scale decreasing by a small fraction
    public Structs.Polygon assignAndAccess(Structs.Polygon poly, Structs.Mesh mesh, Structs.Vertex center) {
        int sumOfDistances = 0;
        int average;
        DistanceProperty getDistance = new DistanceProperty();
        Set<Structs.Vertex> polyVertexList = new AssignVertices(mesh, poly, center).assignAndAccess(); //the updated vertex list for a given polygon

        //get the average of the values of each vertex
        for (Structs.Vertex v : polyVertexList) {
            sumOfDistances += Double.valueOf(getDistance.extract(v.getPropertiesList()).get());
        }

        average = sumOfDistances / polyVertexList.size();

        //based off this average, get an elevationLevel associated with the polygon
        int elevationLevel = new CraterElevation().calcElevation(500, average);

        //adds elevationLevel as a property as well as a new shade for the given polygon
        return propertyAssigner(poly, elevationLevel);
    }
}