package polygonType;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class LandPolygons implements TypePolygons<Integer>{
    List<Structs.Polygon> landPolygons = new ArrayList<>();

    @Override
    public List<Structs.Polygon> make(List<Structs.Polygon> polyList, List<Integer> inIsland){
        List<Structs.Polygon> polygonList = new ArrayList<>(polyList);
        for(Integer i: inIsland){
            Structs.Polygon polygon = polyList.get(i);
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygon);
            String color = "102,141,60";
            String polygonType = "land";
            Structs.Property p = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
            Structs.Property type = Structs.Property.newBuilder().setKey("type").setValue(polygonType).build();

            Structs.Polygon islandPoly= pc.addProperties(p).addProperties(type).build();
            polygonList.set(i, islandPoly);
        }
        return polygonList;
    }
}
