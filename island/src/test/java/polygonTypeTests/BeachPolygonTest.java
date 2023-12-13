package polygonTypeTests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import polygonType.BeachPolygon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeachPolygonTest {
    @Test
    public void testMake() {
        List<Structs.Polygon> polyList = new ArrayList<>();
        Structs.Polygon.Builder polyBuilder = Structs.Polygon.newBuilder();
        Structs.Property.Builder propBuilder = Structs.Property.newBuilder();

        Structs.Polygon oceanPoly = polyBuilder.addNeighborIdxs(1).build();
        Structs.Property oceanTypeProp = propBuilder.setKey("type").setValue("ocean").build();
        oceanPoly = oceanPoly.toBuilder().addProperties(oceanTypeProp).build();
        polyList.add(oceanPoly);

        Structs.Polygon lagoonPoly = polyBuilder.addNeighborIdxs(0).addNeighborIdxs(2).build();
        Structs.Property lagoonTypeProp = propBuilder.setKey("type").setValue("lagoon").build();
        lagoonPoly = lagoonPoly.toBuilder().addProperties(lagoonTypeProp).build();
        polyList.add(lagoonPoly);

        Structs.Polygon landPoly = polyBuilder.addNeighborIdxs(1).build();
        Structs.Property landTypeProp = propBuilder.setKey("type").setValue("land").build();
        landPoly = landPoly.toBuilder().addProperties(landTypeProp).build();
        polyList.add(landPoly);

        List<Integer> inIsland = new ArrayList<>();
        inIsland.add(2);
        BeachPolygon beachPolygon = new BeachPolygon();
        List<Structs.Polygon> result = beachPolygon.make(polyList, inIsland);

        Structs.Polygon beachPoly = result.get(2);
        Structs.Property beachColorProp = propBuilder.setKey("rgb_color").setValue("249,209,153").build();
        assertEquals(beachPoly.getPropertiesList().contains(beachColorProp), true);
    }
}