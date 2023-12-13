package populate;

import biomes.Biome;
import configuration.ConfigurationReader;
import moisture.LakeGenerator;
import moisture.AquiferGenerator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import elevation.ElevationProfile;
import org.locationtech.jts.geom.Coordinate;
import polygonType.LandPolygons;
import polygonType.OceanPolygons;
import rivers.RiverGenerator;
import rivers.UpdateVertexList;
import soil.SoilProfile;
//import urbanism.Cities;

import java.util.List;

public class IslandPopulator implements Populator{

    ConfigurationReader configReader = new ConfigurationReader();
    @Override
    public Structs.Mesh populate(Configuration config, Structs.Mesh mesh, List<Coordinate> polygons, List<Integer> inIslandIndices, List<Integer> inOceanIndices) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        List<Structs.Polygon> meshPolygons = mesh.getPolygonsList();

        //Origin of the island
        Coordinate origin = new Coordinate(960,540);
        Structs.Vertex.Builder builder = Structs.Vertex.newBuilder();
        Structs.Vertex originVertex = builder.setX(origin.getX()).setY(origin.getY()).build();

        //First create the island polygons
        //Then with the land polygons, get the elevation level and elevation colour associated with each one by calling the elevation profile methods
        List<Structs.Polygon> inIslandPolygons = new LandPolygons().make(meshPolygons, inIslandIndices); //RETURNS THE FULL CUMULATIVE ISLAND POLYGONS, WITH ONLY SOME REPLACED WITH ISLAND
        ElevationProfile elevationSpecification = (ElevationProfile) configReader.elevationProfile(config, mesh);
        List<Structs.Polygon> elevatedPolygons = elevationSpecification.make(inIslandPolygons, mesh, originVertex);
        List<Structs.Vertex> updatedVertices = new UpdateVertexList().updateMeshVertices(mesh, elevatedPolygons, originVertex);

        clone.addAllVertices(updatedVertices);

        //Make lakes and aquifers
        int numLakes = configReader.numLakes(config);
        int numAquifers = configReader.numAquifers(config);
        List<Structs.Polygon> lakePolygons = new LakeGenerator().generate(elevatedPolygons, numLakes);
        List<Structs.Polygon> aquiferPolygons = new AquiferGenerator().generate(lakePolygons, numAquifers);

        //Add the remaining ocean polygons
        List<Structs.Polygon> inOceanPolygons = new OceanPolygons().make(aquiferPolygons, inOceanIndices);
        clone.addAllPolygons(inOceanPolygons);
        clone.addAllSegments(mesh.getSegmentsList());

        //Adding rivers
        int numRivers = configReader.numRivers(config);
        Structs.Mesh beforeRiverClone = clone.build();
        List<Structs.Segment> riverSegments = new RiverGenerator(config).generate(beforeRiverClone, inOceanPolygons, numRivers);

        //Adding soil profile
        SoilProfile soilSpecification = (SoilProfile) configReader.soilProfile(config, mesh);
        List<Structs.Polygon> absorbedPolygons = soilSpecification.make(mesh, inOceanPolygons);

        //Include biome
        Biome biomeGenerator = (Biome) configReader.getBiome(config, mesh);
        List<Structs.Polygon> biomePolygons = biomeGenerator.makeBiome(absorbedPolygons);

//        //Include cities as graph
//        int numCities = configReader.numCities(config);
//        List<Structs.Polygon> cityGraph = new Cities().graphConversion(mesh, elevatedPolygons, numCities);

        Structs.Mesh.Builder afterRiverClone = Structs.Mesh.newBuilder();

        afterRiverClone.addAllVertices(updatedVertices);
        afterRiverClone.addAllPolygons(biomePolygons);
        afterRiverClone.addAllSegments(riverSegments);
//        afterRiverClone.addAllPolygons(cityGraph);

        return afterRiverClone.build();
    }
}
