package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Volcano implements ElevationProfile {
    Structs.Mesh mesh;
    public Volcano(Structs.Mesh mesh) {
        this.mesh = mesh;
    }

    //called by ElevationChooser
    public Volcano(Map<String, String> options, Structs.Mesh aMesh){
        this(aMesh);
    }
    @Override
    public List<Structs.Polygon> make(List<Structs.Polygon> meshPolygons, Structs.Mesh mesh, Structs.Vertex center){
        List<Structs.Polygon> newUpdatedPolygons = new ArrayList<>(meshPolygons);

        //overwrite the properties in landPolygon
        for(int i = 0; i<meshPolygons.size(); i++){
            Optional<String> type = new TypeProperty().extract(meshPolygons.get(i).getPropertiesList());
            if(type.isPresent() && type.get().equals("land")){
                //replace each polygon with the volcano criteria
                newUpdatedPolygons.set(i, new AssignVolPolygon().assignAndAccess(meshPolygons.get(i), mesh, center));
            }
        }
        return newUpdatedPolygons;
    }
}
