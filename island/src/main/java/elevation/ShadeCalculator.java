package elevation;

public class ShadeCalculator {

    //Calculates shade of land polygon based off elevation level
    public String calcShade(String ogColor, int elevationLevel){
        int shadeFactor = 1;

        String[] raw = ogColor.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);

        if(elevationLevel == 1){
            red = red * shadeFactor * 1/5;
            green = green * shadeFactor * 1/5;
            blue = blue * shadeFactor  * 1/5;
        } else if(elevationLevel == 2){
            red = red * shadeFactor * 1/4;
            green = green * shadeFactor  * 1/4;
            blue = blue * shadeFactor * 1/4;
        } else if(elevationLevel == 3){
            red = red * shadeFactor * 1/2;
            green = green * shadeFactor * 1/2;
            blue = blue * shadeFactor * 1/2;
        } else if(elevationLevel == 4){
            red = red * shadeFactor * 3/4;
            green = green * shadeFactor * 3/4;
            blue = blue * shadeFactor * 3/4; //light
        } else if (elevationLevel == 5) {
            red = red * shadeFactor;
            green = green * shadeFactor;
            blue = blue * shadeFactor;
        } else{
            throw new IllegalArgumentException("Not a level");
        }
        String shade = red + "," + green + "," + blue;
        return shade;
    }
}
