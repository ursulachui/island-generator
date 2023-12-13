package elevationTests;

import elevation.ShadeCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShadeCalculatorTest {
    @Test
    public void levelOne() {
        ShadeCalculator shadeCalc = new ShadeCalculator();
        String ogColor = "200,200,200";
        int elevationLevel = 1;
        assertEquals("40,40,40", shadeCalc.calcShade(ogColor, elevationLevel));
    }

    @Test
    public void levelTwo() {
        ShadeCalculator shadeCalc = new ShadeCalculator();
        String ogColor = "200,200,200";
        int elevationLevel = 2;
        assertEquals("50,50,50", shadeCalc.calcShade(ogColor, elevationLevel));
    }

    @Test
    public void levelThree() {
        ShadeCalculator shadeCalc = new ShadeCalculator();
        String ogColor = "200,200,200";
        int elevationLevel = 3;
        assertEquals("100,100,100", shadeCalc.calcShade(ogColor, elevationLevel));
    }

    @Test
    public void levelFour() {
        ShadeCalculator shadeCalc = new ShadeCalculator();
        String ogColor = "200,200,200";
        int elevationLevel = 4;
        assertEquals("150,150,150", shadeCalc.calcShade(ogColor, elevationLevel));
    }

    @Test
    public void levelFive() {
        ShadeCalculator shadeCalc = new ShadeCalculator();
        String ogColor = "200,200,200";
        int elevationLevel = 5;
        assertEquals("200,200,200", shadeCalc.calcShade(ogColor, elevationLevel));
    }

    @Test
    public void invalidLevel() {
        var shadeCalculator = new ShadeCalculator();
        assertThrows(IllegalArgumentException.class,
                () -> {
                    shadeCalculator.calcShade("200,200,200", 6);
                });
    }
}