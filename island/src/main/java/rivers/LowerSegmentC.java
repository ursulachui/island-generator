package rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.AssignVertices;
import org.locationtech.jts.geom.Coordinate;
import properties.DistanceProperty;
import properties.TypeProperty;

import java.util.*;

public class LowerSegmentC implements LowerSegment{

    Coordinate origin = new Coordinate(960, 540);
    Structs.Vertex.Builder builder = Structs.Vertex.newBuilder();
    Structs.Vertex originVertex = builder.setX(origin.getX()).setY(origin.getY()).build();
    int count;
    @Override
    public float segmentDistance(Structs.Mesh mesh, Structs.Segment seg) {

        Structs.Vertex v1_seg = mesh.getVertices(seg.getV1Idx());
        Structs.Vertex v2_seg = mesh.getVertices(seg.getV2Idx());

        Optional<String> distance_v1 = new DistanceProperty().extract(v1_seg.getPropertiesList());
        Optional<String> distance_v2 = new DistanceProperty().extract(v2_seg.getPropertiesList());

        //no value here
        //THESE DISTANCES R NOT PRESENT
        float sum = 0;
        if (distance_v1.isPresent() && distance_v2.isPresent()) {
            sum = Float.valueOf(distance_v1.get()) + Float.valueOf(distance_v2.get());
        }
        return sum;
    }
    @Override
    public List<Structs.Segment> relatedSegs(Structs.Mesh mesh, Structs.Vertex startingVertex) {
//        Set<Structs.Segment> related = new HashSet<>(); // stores the related segments for a vertex
        List<Structs.Segment> related = new ArrayList<>(); // stores the related segments for a vertex


        for (Structs.Segment seg : mesh.getSegmentsList()) {
            Structs.Vertex v1 = mesh.getVertices(seg.getV1Idx());
            Structs.Vertex v2 = mesh.getVertices(seg.getV2Idx());

            //does it starting vertex equal one of the vertices found on the segment
            if (((Double.compare(startingVertex.getX(), v1.getX()) == 0) && Double.compare(startingVertex.getY(), v1.getY()) == 0) || ((Double.compare(startingVertex.getX(), v2.getX()) == 0) && Double.compare(startingVertex.getY(), v2.getY()) == 0)) {  //is this ok
                related.add(seg);
            }
        }
        return related;
    }
    @Override
    public boolean isVertexValid(Structs.Mesh mesh, Structs.Vertex startingVertex) {

        boolean isValid = true;

        for (Structs.Polygon p : mesh.getPolygonsList()) {

            Optional<String> type = new TypeProperty().extract(p.getPropertiesList());

            if (type.isPresent() && ((type.get().equals("ocean") || type.get().equals("lake")))) {
                Set<Structs.Vertex> pVL = new AssignVertices(mesh, p, originVertex).assignAndAccess();
                for (Structs.Vertex v : pVL) {
                    //if the starting vertex equals any of the vertices that belong to ocean or lake
                    if ((Double.compare(startingVertex.getX(), v.getX()) == 0) && (Double.compare(startingVertex.getY(), v.getY()) == 0)) {
                        isValid = false; //shouldn't it stop den??
                    }
                }
            }
        }
        return isValid;
    }
    @Override
    public List<Structs.Segment> lowestSegment(Structs.Mesh mesh, Structs.Vertex startingVertex, Map<Structs.Segment, Integer> reachedSegments) {

        //find the lowestDistance segment and returns it with colour
        Structs.Segment minSegment = null; //some random segment, pls work
        Structs.Segment updatedMinSeg = null;

        List<Structs.Segment> riverSegments = new ArrayList<>();

        while (isVertexValid(mesh, startingVertex)) {

            //For each starting point of the river segment, find the possible paths (segments) the river can flow through
            List<Structs.Segment> relatedSegments = relatedSegs(mesh, startingVertex);

            float min = (float) 9999;

            for (Structs.Segment seg : relatedSegments) {
                if (reachedSegments.containsKey(seg)){
                    count = reachedSegments.get(seg);
                }
                else{
                    count = 0;
                    reachedSegments.put(seg, count);
                }

                //The sum of the two distance values of the vertices that make up the current segment we're iterating through
                //remember greater distance (from the highpoint) is lower elevation
                float currentDistance = segmentDistance(mesh, seg); //should be different for each seg

                if (currentDistance <= min) { //greater distance -> lower elevation
                    min = currentDistance;
                    minSegment = seg;

                    //increase count of segments that are chosen for the river, used later for thickness
                    count++;
                    reachedSegments.replace(seg, count);
                }
            }

            int thicknessFactor = reachedSegments.get(minSegment); //i.e. the number of times a segment is used as a river
            int thicknessInt = thicknessFactor*3;
            Structs.Segment.Builder builder = Structs.Segment.newBuilder(minSegment);
            String color = "3,248,252";
            String type = "river";
            String moisture = "55";
            String thickness = String.valueOf(thicknessInt);
            Structs.Property colorProp = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
            Structs.Property riverProp = Structs.Property.newBuilder().setKey("type").setValue(type).build();
            Structs.Property moistureProp = Structs.Property.newBuilder().setKey("moisture").setValue(moisture).build();
            Structs.Property thicknessProp = Structs.Property.newBuilder().setKey("thickness").setValue(thickness).build();
            updatedMinSeg = builder.addProperties(colorProp).addProperties(riverProp).addProperties(moistureProp).addProperties(thicknessProp).build();
            riverSegments.add(updatedMinSeg); //add the one segment that was lower; remember it's a set so takes care of duplicates

            //Get the next starting vertex
            if ((Double.compare(startingVertex.getX(), mesh.getVertices(updatedMinSeg.getV2Idx()).getX()) == 0) && (Double.compare(startingVertex.getY(), mesh.getVertices(updatedMinSeg.getV2Idx()).getY()) == 0)) {
                //equals min segment v2
                startingVertex = mesh.getVertices(updatedMinSeg.getV1Idx());
//                System.out.println("STARTING VERTEX SHOULD BE V1 " + updatedMinSeg.getV1Idx());

            } else if ((Double.compare(startingVertex.getX(), mesh.getVertices(updatedMinSeg.getV1Idx()).getX()) == 0) && (Double.compare(startingVertex.getY(), mesh.getVertices(updatedMinSeg.getV1Idx()).getY()) == 0)) {

                startingVertex = mesh.getVertices(updatedMinSeg.getV2Idx());

            }
        }
        return riverSegments;
    }
}
