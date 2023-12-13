import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import configuration.ConfigurationReader;

import configuration.Configuration;

import shape.*;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        //need to use configuration class for command line arguments
        Configuration config = new Configuration(args);
        ConfigurationReader configReader = new ConfigurationReader();

        Structs.Mesh aMesh = new MeshFactory().read(config.input());

        Shape shapeSpecification = (Shape) configReader.shape(config, aMesh);
        aMesh = shapeSpecification.build(config);

        new MeshFactory().write(aMesh, config.export(Configuration.OUTPUT));
    }
}
