package biomes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.MoistureProperty;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Peru implements Biome {
    Structs.Mesh mesh;

    public Peru(Structs.Mesh mesh) {
        this.mesh = mesh;
    }

    //called by soilChooser
    public Peru(Map<String, String> options, Structs.Mesh aMesh) {
        this(aMesh);
    }

    @Override
    public List<Structs.Polygon> makeBiome(List<Structs.Polygon> absorbedPolygons){

        List<Structs.Polygon> biomedPolygons = new ArrayList<>(absorbedPolygons);

        int i = 0;
        for(Structs.Polygon p: absorbedPolygons){
            Optional<String> typePoly = new TypeProperty().extract(p.getPropertiesList());
            if(typePoly.isPresent() && typePoly.get().equals("absorbed")){
                Optional<String> moistPoly = new MoistureProperty().extract(p.getPropertiesList());
                Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(p);
                String color = "99,79,32"; //add hawaii color
                Structs.Property newType = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                biomedPolygons.set(i, builder.addProperties(newType).build());
            }
            i++;
        }
        return biomedPolygons;
    }
}
