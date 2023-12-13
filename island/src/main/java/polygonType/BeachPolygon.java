package polygonType;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeachPolygon implements TypePolygons<Integer> {
    public List<Structs.Polygon> make(List<Structs.Polygon> polyList, List<Integer> inIsland) {
        List<Structs.Polygon> polygonList = new ArrayList<>(polyList);

        for(Integer j : inIsland){
            Structs.Polygon p = polyList.get(j);

            for(Integer i : p.getNeighborIdxsList()) {
                Optional<String> type = new TypeProperty().extract(polygonList.get(i).getPropertiesList());

                // as long as one of the neighbours is lagoon or ocean
                if ((type.get().equals("lagoon") || type.get().equals("ocean"))) {
                    Structs.Polygon.Builder poly = Structs.Polygon.newBuilder(p);
                    String color = "249,209,153";
                    Structs.Property prop = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                    Structs.Polygon beachPoly = poly.addProperties(prop).build();
                    polygonList.set(j, beachPoly);
                }
            }
        }
        return polygonList;
    }
}