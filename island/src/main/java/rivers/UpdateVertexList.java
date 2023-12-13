package rivers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.AssignVertices;
import properties.TypeProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UpdateVertexList {

    public List<Structs.Vertex> updateMeshVertices(Structs.Mesh mesh, List<Structs.Polygon> elevatedPolygons, Structs.Vertex originVertex){
        List<Structs.Vertex> updatedVertices = new ArrayList<>(mesh.getVerticesList());

        //Update the mesh's list with the updated vertex list
        for(int i = 0; i < elevatedPolygons.size(); i++){
            Structs.Polygon ep = elevatedPolygons.get(i);
            Optional<String> type = new TypeProperty().extract(ep.getPropertiesList());
            if(type.isPresent() && type.get().equals("land")) {
                AssignVertices AssignV = new AssignVertices(mesh, ep, originVertex);
                Set<Structs.Vertex> polyVS = AssignV.assignAndAccess(); //get vertexSET for a given polygon
                List<Structs.Vertex> polyVL = new ArrayList<>(polyVS); //what if this messes up ordering :(:(

                for (int j = 0; j < polyVL.size(); j++) {
                    //for each vertex in the polygon's vertex list, go through all of mesh's vertices and sees if its equal
                    //if it is, then replace it

                    for (int k = 0; k < mesh.getVerticesList().size(); k++) {
                        if ((polyVL.get(j).getX() == mesh.getVertices(k).getX() && polyVL.get(j).getY() == mesh.getVertices(k).getY())) {
                            updatedVertices.set(k, polyVL.get(j));
                        }
                    }
                }
            }
        }
        return updatedVertices;
    }
}
