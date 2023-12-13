package configuration;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    public static final String INPUT = "i";
    public static final String OUTPUT = "o";
    public static final String SHAPE = "s";
    public static final String MVP = "m";
    public static final String ALTITUDE = "a";
    public static final String LAKES = "l";
    public static final String SOIL = "soil";
    public static final String AQUIFERS = "aq";
    public static final String RIVERS = "r";
    public static final String BIOMES = "b";
    public static final String CITIES = "c";

    private CommandLine cli;
    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for(Option o: cli.getOptions()){
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }
    public String input() {
        return this.cli.getOptionValue(INPUT);
    }
    public String output() {
        return this.cli.getOptionValue(OUTPUT);
    }
    public String export(String key) {
        return cli.getOptionValue(key);
    }
    public boolean shape() { return this.cli.hasOption(SHAPE); }
    public boolean elevation() { return this.cli.hasOption(ALTITUDE); }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file (SVG)"));
        options.addOption(new Option(OUTPUT, true, "Output file (MESH)"));
        options.addOption(new Option(SHAPE, true, "Shape (SHAPE)"));
        options.addOption(new Option(MVP, false, "activate MVP mode"));
        options.addOption(new Option(ALTITUDE, true, "select elevation profile"));
        options.addOption(new Option(LAKES, true, "select number of rivers"));
        options.addOption(new Option(AQUIFERS, true, "select number of aquifers"));
        options.addOption(new Option(RIVERS, true, "select number of rivers"));
        options.addOption(new Option(SOIL, true, "select soil profile"));
        options.addOption(new Option(BIOMES, true, "select biomes profile"));
        options.addOption(new Option(CITIES, true, "select cities"));

        return options;
    }
}
