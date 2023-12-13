package shape;

import JTSconversions.CentroidConvertor;
import configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.*;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Polygon;
import populate.IslandPopulator;
import populate.MVP;


public class Circle implements Shape {
    Structs.Mesh mesh;
    boolean isMVP;
    List<Integer> inIsland = new ArrayList<>();

    public Circle(){}

    public Circle(Boolean MVP, Structs.Mesh mesh) {
        this.mesh = mesh;
        this.isMVP = MVP;
    }

    //this constructor is used by shapeChooser
    public Circle(Map<String, String> options, Structs.Mesh aMesh){
        this(
                options.containsKey(Configuration.MVP),
                aMesh);
    }

    @Override
    public Structs.Mesh build(Configuration config){

        List<Coordinate> polygons = new CentroidConvertor().convertToJTS(mesh);
        List<Integer> inIslandIndices = setShape(polygons);
        List<Integer> inOceanIndices = remainingWater(polygons);

        Structs.Mesh populatedIsland;
        if(isMVP){ //if it contains the -m flag in makefile
            //note we have to choose constructor based on MVP or not, this is only needed for circle though
            populatedIsland = new MVP().populate(config, mesh, polygons, inIslandIndices, inOceanIndices);
        }
        else{
            populatedIsland = new IslandPopulator().populate(config, mesh, polygons, inIslandIndices, inOceanIndices);
        }

        return populatedIsland;
    }

    @Override
    public List setShape(List<Coordinate> polygons) {
        Polygon circlePerimeter = createPerimeter();

        //now checking contains
        GeometryFactory geoFactory = new GeometryFactory();

        for (int i = 0; i < polygons.size(); i++){
            //need to convert coordinate into a point (point extends geometry)
            Point cpoint = geoFactory.createPoint(polygons.get(i)); //converts centroid coordinate to a point

            //if the centroid is within the circle, add the index value to the inIsland list
            if (circlePerimeter.contains(cpoint)){
                inIsland.add(i); //adding the index of this polygon
            }
        }
        return inIsland;
    }

    @Override
    public List remainingWater(List<Coordinate> polygons){
        Polygon circlePerimeter = createPerimeter();
        //now checking contains
        GeometryFactory geoFactory = new GeometryFactory();
        List<Integer> inOcean = new ArrayList<>();

        for (int i = 0; i < polygons.size(); i++){
            //need to convert coordinate into a point (point extends geometry)

            Point cpoint = geoFactory.createPoint(polygons.get(i)); //converts centroid coordinate to a point

            //if the centroid is NOT within the circle, add the index value to the inOcean list
            if (!circlePerimeter.contains(cpoint)){
                inOcean.add(i);
            }
        }
        return inOcean;
    }

    @Override
    public Polygon createPerimeter(){
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate origin = new Coordinate(960,540);
        Point point = geometryFactory.createPoint(origin);

        int radius = 500;
        int segmentations = 32;

        //create a circle polygon using a point and buffer
        Polygon circle = (Polygon) point.buffer(radius, segmentations);
        return circle;
    }
}