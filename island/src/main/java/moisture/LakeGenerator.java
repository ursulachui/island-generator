package moisture;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.ElevationProperty;
import properties.RemoveProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LakeGenerator implements BodiesOfWater {

    @Override
    public List<Structs.Polygon> generate(List<Structs.Polygon> elevatedPolys, int numLakes){

        List<Structs.Polygon> polygonList = new ArrayList<>(elevatedPolys);
        int count = 0;
        //go through the elevatedPolygon list, and check if the type is 1 (low elevation level), and add a lake

        for(int i = 0; i < elevatedPolys.size(); i++){
            Optional<String> elevationType = new ElevationProperty().extract(elevatedPolys.get(i).getPropertiesList());
            //lakes on low elevation only
            if(count < numLakes) {
                if (elevationType.isPresent() && (elevationType.get().equals("1"))){
                    Structs.Polygon polygon = elevatedPolys.get(i);
                    Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(polygon);

                    String color = "52,98,235";
                    String polygonType = "lake";
                    String moisture = "70";
                    new RemoveProperties().remove(builder, "type"); //remove the type (land)
                    new RemoveProperties().remove(builder, "rgb_color"); //remove elevation shade

                    //add the new lake colour property
                    //add the new lake type property
                    //add the moisture content to the lake polygon
                    Structs.Property lakeColor = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                    Structs.Property type = Structs.Property.newBuilder().setKey("type").setValue(polygonType).build();
                    Structs.Property moistureContent = Structs.Property.newBuilder().setKey("moisture").setValue(moisture).build();

                    Structs.Polygon lakePoly = builder.addProperties(lakeColor).addProperties(type).addProperties(moistureContent).build();
                    polygonList.set(i, lakePoly); //replace the original mesh list with the lake polygons

                    count++;
                }
            }
        }
        return polygonList;
    }
}
