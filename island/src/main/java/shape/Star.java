package shape;

import JTSconversions.CentroidConvertor;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import populate.IslandPopulator;
import populate.MVP;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Star implements Shape{
    Structs.Mesh mesh;
    List<Integer> inIsland = new ArrayList<>();

   
    public Star(Structs.Mesh mesh) {
        this.mesh = mesh;
    }

    public Star(Map<String, String> options, Structs.Mesh aMesh){
        this(
                aMesh);
    }


    @Override
    public Structs.Mesh build(Configuration config) {
        List<Coordinate> polygons = new CentroidConvertor().convertToJTS(mesh);
        List<Integer> inIslandIndices = setShape(polygons);
        List<Integer> inOceanIndices = remainingWater(polygons);

        Structs.Mesh populatedIsland = new IslandPopulator().populate(config, mesh, polygons, inIslandIndices, inOceanIndices);
        return populatedIsland;
    }

    @Override
    public List setShape(List<Coordinate> polygons) {
        Polygon starPerimeter = createPerimeter();

        //now checking contains
        GeometryFactory geoFactory = new GeometryFactory();

        for (int i = 0; i < polygons.size(); i++){
            //need to convert coordinate into a point (point extends geometry)
            Point cpoint = geoFactory.createPoint(polygons.get(i)); //converts centroid coordinate to a point

            //if the centroid is within the circle, add the index value to the inIsland list
            if (starPerimeter.contains(cpoint)){
                inIsland.add(i); //adding the index of this polygon
            }
        }
        return inIsland;
    }

    @Override
    public List remainingWater(List<Coordinate> polygons) {
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
    public Polygon createPerimeter() {
        GeometryFactory geometryFactory = new GeometryFactory();

        int radius = 500;
        Coordinate center = new Coordinate(960, 540);

        Coordinate[] vertices = new Coordinate[11]; // 10 + 1 because we need to have start coord repeated at the end
        double angleInc = 72 * Math.PI / 180; //converts to radians. angles in a pentagon are 36 deg
        double angle = -Math.PI / 2; // -90 deg starting angle


        for (int i = 0; i < 10; i+=2) {
            double x = center.x + radius * Math.cos(angle);
            double y = center.y + radius * Math.sin(angle);

            double x2 = center.x + radius/2 * Math.cos(angle + (36 * Math.PI));
            double y2 = center.y + radius/2 * Math.sin(angle + (36 * Math.PI));

            vertices[i] = new Coordinate(x, y);
            vertices[i+1] = new Coordinate(x2, y2);

            angle += angleInc;

            if (i == 0){
                vertices[10] = new Coordinate(x, y);
            }
        }
        for (int j = 0; j<vertices.length; j++){
            if (vertices[j] == null){
                System.out.println("" + j + "th vertex is null");
            }
        }
        // use LinearRing to connect vertices to make an outline
        LinearRing ring = geometryFactory.createLinearRing(vertices);
        // need to convert into Geometry.Polygon using geometry factory
        Polygon star = geometryFactory.createPolygon(ring, null);
        return star;
    }
}
