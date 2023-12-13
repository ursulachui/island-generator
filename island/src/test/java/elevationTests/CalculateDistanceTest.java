package elevationTests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.CalculateDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateDistanceTest {
    @Test
    public void calcDistance() {
        Structs.Vertex center = Structs.Vertex.newBuilder().setX(0.0).setY(0.0).build();
        Structs.Vertex v1 = Structs.Vertex.newBuilder().setX(3.0).setY(4.0).build();

        // create a CalculateDistance object
        CalculateDistance calcDist = new CalculateDistance();

        // assert that the actual distance matches the expected distance
        assertEquals(5.0, calcDist.calcDistance(center, v1), 0.001);
    }

    @Test
    public void invalidDistance() {
        var calcDistance = new CalculateDistance();
        assertThrows(NullPointerException.class,
                () -> {
                    calcDistance.calcDistance(null, null);
                });
    }
}