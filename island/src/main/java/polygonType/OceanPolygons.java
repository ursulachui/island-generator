package polygonType;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class OceanPolygons implements TypePolygons<Integer>{
    List<Structs.Polygon> oceanPolygons = new ArrayList<>();

    @Override
    public List<Structs.Polygon> make(List<Structs.Polygon> polyList, List<Integer> inOcean){
        List<Structs.Polygon> polygonList = new ArrayList<>(polyList);
        for(Integer i: inOcean){
            Structs.Polygon polygon = polyList.get(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygon);
            String color = "19,146,215";
            String polygonType = "ocean";
            Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
            Structs.Property type = Structs.Property.newBuilder().setKey("type").setValue(polygonType).build();

            Structs.Polygon oceanPoly= pc.addProperties(p).addProperties(type).build();
            polygonList.set(i, oceanPoly);
        }
        return polygonList;
    }
}
