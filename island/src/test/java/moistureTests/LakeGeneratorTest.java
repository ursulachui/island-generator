package moistureTests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import moisture.LakeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LakeGeneratorTest {
    @Test
    public void testGenerate() {
        List<Structs.Polygon> elevatedPolys = new ArrayList<>();
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        Structs.Property elevation = Structs.Property.newBuilder().setKey("elevation").setValue("1").build();
        builder.addProperties(elevation);
        elevatedPolys.add(builder.build());

        LakeGenerator lakeGenerator = new LakeGenerator();
        List<Structs.Polygon> lakes = lakeGenerator.generate(elevatedPolys, 1);

        assertEquals(1, lakes.size());
        Structs.Polygon lake = lakes.get(0);
        assertTrue(lake.getPropertiesList().stream().anyMatch(p -> p.getKey().equals("type") && p.getValue().equals("lake")));
        assertTrue(lake.getPropertiesList().stream().anyMatch(p -> p.getKey().equals("moisture") && p.getValue().equals("70")));
        assertTrue(lake.getPropertiesList().stream().anyMatch(p -> p.getKey().equals("rgb_color") && p.getValue().equals("52,98,235")));
    }
}