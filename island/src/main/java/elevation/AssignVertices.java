package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;
import java.util.Set;

public class AssignVertices {
    Set<Structs.Vertex> vertexList = new HashSet<>();
    CalculateDistance calculator = new CalculateDistance();
    Structs.Polygon poly;
    Structs.Vertex center;
    Structs.Mesh mesh;

    //get the vertex list with properties from a given polygon
    public AssignVertices(Structs.Mesh mesh, Structs.Polygon poly, Structs.Vertex center){
        this.poly = poly;
        this.center = center;
        this.mesh = mesh;
    }


    //assigns distance property to a vertex
    public Structs.Vertex propertyAssigner(Structs.Vertex vertex){
        //use the calculator here to calculate the distance (represents elevation level, each vertex has distinct level)
        Structs.Vertex.Builder newVertex = Structs.Vertex.newBuilder(vertex);
        String distance = String.valueOf(calculator.calcDistance(center, vertex));
        Structs.Property prop = Structs.Property.newBuilder().setKey("distance").setValue(distance).build();
        Structs.Vertex pVertex = newVertex.addProperties(prop).build();
        return pVertex; //returns a vertex with a property
    }

    //assign and access the vertex list given a polygon
    public Set<Structs.Vertex> assignAndAccess(){
        for(Integer segInd: poly.getSegmentIdxsList()){
            Structs.Segment seg = mesh.getSegments(segInd);
                Structs.Vertex v1 = mesh.getVertices(seg.getV1Idx());
                Structs.Vertex v2 = mesh.getVertices(seg.getV2Idx());
                vertexList.add(propertyAssigner(v1));
                vertexList.add(propertyAssigner(v2));
        }
        return vertexList;
    }
}
