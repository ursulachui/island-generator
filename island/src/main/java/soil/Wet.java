package soil;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.GetPolygon;
import properties.MoistureProperty;
import properties.RemoveProperties;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Wet implements SoilProfile {
    Structs.Mesh mesh;

    public Wet(Structs.Mesh mesh) {
        this.mesh = mesh;
    }

    //called by soilChooser
    public Wet(Map<String, String> options, Structs.Mesh aMesh) {
        this(aMesh);
    }


    @Override
    public List<Structs.Polygon> make(Structs.Mesh mesh, List<Structs.Polygon> polyList) {
        List<Structs.Polygon> updatedPolygons = new ArrayList<>(polyList);

        int i = 0;

        for (Structs.Polygon p : polyList) {
            Optional<String> typePoly = new TypeProperty().extract(p.getPropertiesList());
            if((typePoly.get().equals("land"))) {

                for (Integer j : p.getNeighborIdxsList()) {
                    Optional<String> type = new TypeProperty().extract(polyList.get(j).getPropertiesList());
                    if (type.isPresent() && ((type.get().equals("aquifer") || type.get().equals("lake")))) {
                        // as long as one of the neighbours is aquifer or lake
                        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(p);
                        new RemoveProperties().remove(builder, "rgb_color"); //remove the color (only for testing)
                        new RemoveProperties().remove(builder, "type"); //remove the color (only for testing)

                        Optional<String> moistness = new MoistureProperty().extract(polyList.get(j).getPropertiesList());
                        Structs.Polygon.Builder poly = Structs.Polygon.newBuilder(polyList.get(i));
                        String absorption = String.valueOf(new WetCalculator().calculate(moistness.get()));
                        String typeOfSoil = "absorbed";
                        Structs.Property newType = Structs.Property.newBuilder().setKey("type").setValue(typeOfSoil).build();
                        Structs.Property abs = Structs.Property.newBuilder().setKey("moisture").setValue(absorption).build();
                        Structs.Polygon wetPoly = poly.addProperties(abs).addProperties(newType).build();
                        updatedPolygons.set(i, wetPoly);
                    }
                }
            }
            i++;
        }
        return updatedPolygons;
    }
}

