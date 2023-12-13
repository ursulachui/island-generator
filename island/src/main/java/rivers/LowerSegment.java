package rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.AssignVertices;
import org.locationtech.jts.geom.Coordinate;
import properties.DistanceProperty;
import properties.TypeProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;


//returns you the segment with the lowest distance sum given a vertex
public interface LowerSegment {
    public float segmentDistance(Structs.Mesh mesh, Structs.Segment seg);
    public List<Structs.Segment> relatedSegs(Structs.Mesh mesh, Structs.Vertex startingVertex);
    public boolean isVertexValid(Structs.Mesh mesh, Structs.Vertex startingVertex);
    public List<Structs.Segment> lowestSegment(Structs.Mesh mesh, Structs.Vertex startingVertex, Map<Structs.Segment, Integer> reachedSegments);

}
