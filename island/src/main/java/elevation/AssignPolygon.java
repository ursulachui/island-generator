package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import properties.ColorProperty;
import properties.RemoveProperties;
import properties.TypeProperty;

import java.util.Optional;

public class AssignPolygon {
    //assigns elevation property to a polygon and return that polygon
    public Structs.Polygon propertyAssigner(Structs.Polygon poly, int elevationLevel){
        //use the calculator here to calculate the distance (represents elevation level, each vertex has distinct level)
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder(poly);

        Structs.Polygon elevatedPoly;

        for(int i = 0; i<poly.getPropertiesList().size(); i++){
            //extracts the colour from the polygon, and with that, calculate the shade needed for the given elevation
            Optional<String> typeExtract = new TypeProperty().extract(poly.getPropertiesList());
            Optional<String> colorExtract = new ColorProperty().extract(poly.getPropertiesList());
            String color = new ShadeCalculator().calcShade(colorExtract.get(), elevationLevel);
            if(typeExtract.get().equals("land")){
                new RemoveProperties().remove(builder, "rgb_color");
                Structs.Property colorStuff = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
                elevatedPoly = builder.addProperties(colorStuff).build();
            }
        }

        //add temperature property for the polygon
        String temp = "0";
        if (elevationLevel == 1) {
            temp = "30";
        }
        else if (elevationLevel == 2) {
            temp = "28";
        }
        else if (elevationLevel == 3) {
            temp = "26";
        }
        else if (elevationLevel == 4) {
            temp = "24";
        }
        else if (elevationLevel == 5) {
            temp = "22";
        }
        Structs.Property temperature = Structs.Property.newBuilder().setKey("temperature").setValue(temp).build();

        //add elevation property for the polygon
        Structs.Property elevation = Structs.Property.newBuilder().setKey("elevation").setValue(String.valueOf(elevationLevel)).build();
        elevatedPoly = builder.addProperties(elevation).addProperties(temperature).build();

        return elevatedPoly; //returns the polygon with elevation and shade property
    }
}
