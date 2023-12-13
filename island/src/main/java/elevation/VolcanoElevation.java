package elevation;

public class VolcanoElevation implements ElevationLevel {

    public int calcElevation(int radius, int average){
        //radius represents the farthest distance from the center of island
        //divide by 5 to split into 5 elevation levels

        int stage = radius/5;

        if(average <= stage){
            return 5; //high elevation towards the center of island (volcano)
        } else if (average <= stage * 2){
            return 4;
        } else if (average <= stage * 3){
            return 3;
        } else if (average <= stage * 4){
            return 2;
        } else if(average <= stage * 5){
            return 1;
        } else {
            throw new IllegalArgumentException("Average out of range");
        }
    }
}
