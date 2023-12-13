package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Crater implements ElevationProfile {
    Structs.Mesh mesh;
    public Crater(Structs.Mesh mesh) {
        this.mesh = mesh;
    }

    //called by ElevationChooser
    public Crater(Map<String, String> options, Structs.Mesh aMesh){
        this(aMesh);
    }
    @Override
    public List<Structs.Polygon> make(List<Structs.Polygon> meshPolygons, Structs.Mesh mesh, Structs.Vertex center){
        List<Structs.Polygon> newUpdatedPolygons = new ArrayList<>(meshPolygons);

        //overwrite the properties in landPolygon
        for(int i = 0; i<meshPolygons.size(); i++){

            Optional<String> type = new TypeProperty().extract(meshPolygons.get(i).getPropertiesList());

            //change the properties only if it's a land polygon
            if(type.isPresent() && type.get().equals("land")){
                //replace the polygon in the meshPolygons list
                newUpdatedPolygons.set(i, new AssignCrPolygon().assignAndAccess(meshPolygons.get(i), mesh, center));
            }
        }
        return newUpdatedPolygons;
    }
}
