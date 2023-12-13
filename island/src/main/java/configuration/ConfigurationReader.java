package configuration;


import biomes.Hawaii;
import biomes.Peru;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.*;
import rivers.LowerSegmentV;
import rivers.LowerSegmentC;
import shape.Circle;
import shape.Star;
import soil.Dry;
import soil.Wet;


import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {
    //PARSES AND RETURNS THE INFORMATION FROM OUR CONFIGURATION
    //SHAPE TYPE, ELEVATION PROFILE, NUMBER OF LAKES, NUMBER OF AQUIFERS

    private static final Map<String, Class> shapeBindings = new HashMap<>();
    private static final Map<String, Class> elevationBindings = new HashMap<>();
    private static final Map<String, Class> soilBindings = new HashMap<>();
    private static final Map<String, Class> riverBindings = new HashMap<>();
    private static final Map<String, Class> biomeBindings = new HashMap<>();

    static {
        shapeBindings.put("circle", Circle.class);
        shapeBindings.put("star", Star.class);
        elevationBindings.put("volcano", Volcano.class);
        elevationBindings.put("crater", Crater.class);
        soilBindings.put("wet", Wet.class);
        soilBindings.put("dry", Dry.class);
        riverBindings.put("volcano", LowerSegmentV.class);
        riverBindings.put("crater", LowerSegmentC.class);
        biomeBindings.put("hawaii", Hawaii.class);
        biomeBindings.put("peru", Peru.class);
    }

    //returns the shape class
    public static Object shape(configuration.Configuration config, Structs.Mesh aMesh) {
        Map<String, String> options = config.export();

        try {
            Class klass = shapeBindings.get(options.get(Configuration.SHAPE)); //klass is a class, since bindings is <String, Class>
            System.out.println(klass);
            return klass.getDeclaredConstructor(Map.class, Structs.Mesh.class).newInstance(options, aMesh); //Map.class is an indication of the parameter type for the constructor
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //returns the elevation profile class
    public static Object elevationProfile(configuration.Configuration config, Structs.Mesh aMesh) {
        Map<String, String> options = config.export();

        try {
            Class klass = elevationBindings.get(options.get(Configuration.ALTITUDE)); //klass is a class, since bindings is <String, Class>
            return klass.getDeclaredConstructor(Map.class, Structs.Mesh.class).newInstance(options, aMesh); //Map.class is an indication of the parameter type for the constructor
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //choosing river generation style is based on elevation type (crater's require the inverted style)
    public static Object riverGeneration(configuration.Configuration config, Structs.Mesh aMesh) {
        Map<String, String> options = config.export();

        try {
            Class klass = riverBindings.get(options.get(Configuration.ALTITUDE)); //based on ALTITUDE command line function
            return klass.getDeclaredConstructor().newInstance(); //Map.class is an indication of the parameter type for the constructor
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //returns the number of lakes
    public static int numLakes(Configuration config) {
        Map<String, String> options = config.export();

        try {
            int numLakes = Integer.parseInt(options.get(Configuration.LAKES));
            return numLakes;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //returns the number of aquifers
    public static int numAquifers(Configuration config) {
        Map<String, String> options = config.export();

        try {
            int num = Integer.parseInt(options.get(Configuration.AQUIFERS));
            return num;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int numRivers(Configuration config) {
        Map<String, String> options = config.export();

        try {
            int num = Integer.parseInt(options.get(Configuration.RIVERS));
            return num;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    //returns soil profile class
    public static Object soilProfile(configuration.Configuration config, Structs.Mesh aMesh) {
        Map<String, String> options = config.export();

        try {
            Class klass = soilBindings.get(options.get(Configuration.SOIL)); //klass is a class, since bindings is <String, Class>
            return klass.getDeclaredConstructor(Map.class, Structs.Mesh.class).newInstance(options, aMesh); //Map.class is an indication of the parameter type for the constructor
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Object getBiome(configuration.Configuration config, Structs.Mesh aMesh) {
        Map<String, String> options = config.export();

        try {
            Class klass = biomeBindings.get(options.get(Configuration.BIOMES)); //klass is a class, since bindings is <String, Class>
            return klass.getDeclaredConstructor(Map.class, Structs.Mesh.class).newInstance(options, aMesh); //Map.class is an indication of the parameter type for the constructor
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int numCities(Configuration config) {
        Map<String, String> options = config.export();

        try {
            int num = Integer.parseInt(options.get(Configuration.CITIES));
            return num;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
