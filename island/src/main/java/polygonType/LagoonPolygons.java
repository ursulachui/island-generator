package polygonType;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.TypeProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LagoonPolygons implements TypePolygons<Integer>{
    @Override
    public List<Structs.Polygon> make(List<Structs.Polygon> polyList, List<Integer> inLagoon){
        List<Structs.Polygon> polygonList = new ArrayList<>(polyList);
        for(Integer i : inLagoon){
            Structs.Polygon polygon = polyList.get(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygon);
            String color = "76,183,165";
            String polygonType = "lagoon";
            Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
            Structs.Property type = Structs.Property.newBuilder().setKey("type").setValue(polygonType).build();

            Structs.Polygon lagoonPoly = pc.addProperties(p).addProperties(type).build();
            polygonList.set(i, lagoonPoly);
        }
        return polygonList;
    }
}
