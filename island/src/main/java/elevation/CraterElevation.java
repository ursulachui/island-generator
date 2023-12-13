package elevation;

public class CraterElevation implements ElevationLevel {
    @Override
    public int calcElevation(int radius, int average){
        //radius represents the farthest distance from the center of island
        //divide by 5 to split into 5 elevation levels

        int stage = radius/5;

        if(average <= stage){
            return 1; //low elevation if you're at the center of island (crater)
        } else if (average <= stage * 2){
            return 2;
        } else if (average <= stage * 3){
            return 3;
        } else if (average <= stage * 4){
            return 4;
        } else if(average <= stage * 5){
            return 5;
        }else {
            throw new IllegalArgumentException("Average out of range");
        }
    }
}
