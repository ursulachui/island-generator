package elevationTests;

import elevation.CraterElevation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CraterElevationTest {
    @Test
    void levelOne(){
        var craterElevation = new CraterElevation();
        assertEquals(1, craterElevation.calcElevation(500, 82));
    }

    @Test
    void levelTwo(){
        var craterElevation = new CraterElevation();
        assertEquals(2, craterElevation.calcElevation(500, 150));
    }

    @Test
    void levelThree(){
        var craterElevation = new CraterElevation();
        assertEquals(3, craterElevation.calcElevation(500, 280));
    }

    @Test
    void levelFour(){
        var craterElevation = new CraterElevation();
        assertEquals(4, craterElevation.calcElevation(500, 400));
    }

    @Test
    void levelFive(){
        var craterElevation = new CraterElevation();
        assertEquals(5, craterElevation.calcElevation(500, 499));
    }

    @Test
    void invalidAverage(){
        var craterElevation = new CraterElevation();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    craterElevation.calcElevation(600, 901);
                });
    }

}