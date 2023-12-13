package soil;

public class DryCalculator implements AbsorptionCalculator{
    public double calculate(String moistness){
        int moisture = Integer.valueOf(moistness);
        return moisture*0.1;
    }
}
