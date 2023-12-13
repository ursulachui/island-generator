package moisture;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import properties.ElevationProperty;
import properties.RemoveProperties;

import java.util.*;

public class AquiferGenerator implements BodiesOfWater{

    @Override
    public List<Structs.Polygon> generate(List<Structs.Polygon> polyList, int numAquifers){

        List<Structs.Polygon> polygonList = new ArrayList<>(polyList);
        int count = 0;
        Random rand = new Random();

        //go through the elevatedPolygon list, and check if the type is 1 (low elevation level), and add a lake
        for(int i = 0; i < polyList.size(); i++){
            Optional<String> elevationType = new ElevationProperty().extract(polyList.get(i).getPropertiesList());
            if(count < numAquifers) {
                int randomElevation = rand.nextInt(5) + 1; //generate a random elevation level from 1-5
                if (elevationType.isPresent() && (elevationType.get().equals(String.valueOf(randomElevation)))){
                    Structs.Polygon polygon = polyList.get(i);
                    Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(polygon);

                    String polygonType = "aquifer";
                    String moisture = "40";
                    new RemoveProperties().remove(builder, "type"); //remove the type (land)

                    //add the new lake type property
                    //add the moisture content to the lake polygon
                    Structs.Property type = Structs.Property.newBuilder().setKey("type").setValue(polygonType).build();
                    Structs.Property moistureContent = Structs.Property.newBuilder().setKey("moisture").setValue(moisture).build();

                    Structs.Polygon aquiferPoly = builder.addProperties(type).addProperties(moistureContent).build();
                    polygonList.set(i, aquiferPoly); //replace the original mesh list with the lake polygons

                    count++;
                }
            }
        }
        return polygonList;
    }
}
