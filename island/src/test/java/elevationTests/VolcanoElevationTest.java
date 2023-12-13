package elevationTests;

import elevation.VolcanoElevation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolcanoElevationTest {

    @Test
    void levelOne(){
        var volcanoElevation = new VolcanoElevation();
        assertEquals(1, volcanoElevation.calcElevation(500, 500));
    }

    @Test
    void levelTwo(){
        var volcanoElevation = new VolcanoElevation();
        assertEquals(2, volcanoElevation.calcElevation(500, 400));
    }

    @Test
    void levelThree(){
        var volcanoElevation = new VolcanoElevation();
        assertEquals(3, volcanoElevation.calcElevation(500, 300));
    }

    @Test
    void levelFour(){
        var volcanoElevation = new VolcanoElevation();
        assertEquals(4, volcanoElevation.calcElevation(500, 123));
    }

    @Test
    void levelFive(){
        var volcanoElevation = new VolcanoElevation();
        assertEquals(5, volcanoElevation.calcElevation(500, 23));
    }

    @Test
    void invalidAverage(){
        var volcanoElevation = new VolcanoElevation();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    volcanoElevation.calcElevation(400, 401);
                });
    }

}