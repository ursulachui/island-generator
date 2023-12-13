package rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import configuration.ConfigurationReader;
import elevation.AssignVertices;
import elevation.ElevationProfile;
import org.locationtech.jts.geom.Coordinate;
import properties.ElevationProperty;
import properties.TypeProperty;

import java.util.*;

public class RiverGenerator {
    Configuration config;

    //Origin of the island
    Coordinate origin = new Coordinate(960,540);
    Structs.Vertex.Builder builder = Structs.Vertex.newBuilder();
    Structs.Vertex originVertex = builder.setX(origin.getX()).setY(origin.getY()).build();
    Map<Structs.Segment, Integer> reachedSegments = new HashMap<>();


    public RiverGenerator(Configuration config){
        this.config = config;
    }

    public List<Structs.Segment> generate(Structs.Mesh mesh, List<Structs.Polygon> updatedPolygons, int numRivers){

        int riverCount = 0;
        Random rand = new Random();
        //going to replace these with the updated river, coloured segments
        List<Structs.Segment> updatedSegments = new ArrayList<>(mesh.getSegmentsList());

        while(riverCount < numRivers) {
            Structs.Vertex startingVertex = null;

            //This is where our river will begin to flow
            //Get a random vertex from an elevation 3 land polygon
            //starting vertex is random
            for(int i = 0; i<updatedPolygons.size(); i++){
                Structs.Polygon randPoly = mesh.getPolygons(rand.nextInt(updatedPolygons.size()));
                Optional<String> type = new TypeProperty().extract(randPoly.getPropertiesList());
                Optional<String> elevation = new ElevationProperty().extract(randPoly.getPropertiesList());
                if(type.get().equals("land") && elevation.get().equals("5")){
                    Set<Structs.Vertex> polyVL = new AssignVertices(mesh, randPoly, originVertex).assignAndAccess();
                    List<Structs.Vertex> polyVLList = new ArrayList<>();
                    //does this effect order?
                    polyVLList.addAll(polyVL);
                    startingVertex = polyVLList.get(rand.nextInt(polyVL.size())); //random starting vertex
                    i = updatedPolygons.size(); //stop the for loop
                }
            }

            ConfigurationReader configReader = new ConfigurationReader();
            //have to use chooser for Volcano or Crater (inverted) style river generation
            LowerSegment riverGenerationType = (LowerSegment) configReader.riverGeneration(config, mesh);
            List<Structs.Segment> riverSegments = riverGenerationType.lowestSegment(mesh, startingVertex, reachedSegments);


            //Replacing the mesh's original segment list with our river segments list
            for(int i = 0; i<mesh.getSegmentsList().size(); i++){
                //the lower segment is our river
                for(Structs.Segment riverSeg : riverSegments){
                    if((mesh.getSegments(i).getV1Idx() == riverSeg.getV1Idx()) && (mesh.getSegments(i).getV2Idx() == riverSeg.getV2Idx())){
                        updatedSegments.set(i, riverSeg); //replace the mesh's segment list with our new river segments
                    }
                }
            }
            riverCount++;
        }
        return updatedSegments;
    }
}
