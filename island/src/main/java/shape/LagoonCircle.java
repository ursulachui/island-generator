package shape;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

public class LagoonCircle extends Circle{

    @Override
    public List setShape(List<Coordinate> polygons){
        //need to do some intersection stuff to mark our JTS polygons as "within the island" i.e. shape

        //to do these intersections, use Geometry.contains(Geometry g)
        //essentially checks the parameter g is contained within the geometry

        //we have to make a circle polygon to represent the outer radius of our island
        Polygon circlePerimeter = createPerimeter();

        //now checking contains
        GeometryFactory geoFactory = new GeometryFactory();
        List inLagoon = new ArrayList<>();
        for (int i = 0; i < polygons.size(); i++){
            //need to convert coordinate into a point (point extends geometry)
            Point cpoint = geoFactory.createPoint(polygons.get(i)); //converts centroid coordinate to a point

            //if the centroid is within the circle, add the index value to the inIsland list
            if (circlePerimeter.contains(cpoint)){
                inLagoon.add(i);
            }
        }
        return inLagoon;
    }

    @Override
    public Polygon createPerimeter(){
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate origin = new Coordinate(960,540);
        Point point = geometryFactory.createPoint(origin);

        int radius = 100;
        int segmentations = 32;

        //create a circle polygon using a point and buffer
        Polygon circle = (Polygon) point.buffer(radius, segmentations);

        return circle;
    }

    public List removeShape(List<Integer> outerIdx, List<Integer> innerIdx) {
        List<Integer> inBeachIndices = new ArrayList<>();
        boolean isLand;
        for (Integer i : outerIdx){
            isLand = true;
            for (Integer j : innerIdx){
                if (i.equals(j)){
                    isLand = false;
                }
            }
            if (isLand){
                inBeachIndices.add(i);
            }
        }
        return inBeachIndices;
    }
}
