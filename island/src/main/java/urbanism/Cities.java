//package urbanism;
//
//import ca.mcmaster.cas.se2aa4.a2.io.Structs;
//import graph.Edge;
//import graph.Node;
//import properties.RemoveProperties;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cities implements City {
//    public List<Structs.Polygon> graphConversion(Structs.Mesh aMesh, List<Structs.Polygon> graphPolys, int numCities) {
//        List<Structs.Polygon> polygons = aMesh.getPolygonsList();
//        List<Structs.Polygon> polygonList = new ArrayList<>(graphPolys);
//        List<Edge> edges = new ArrayList<>();
//        List<Edge> newEdges = new ArrayList<>();
//        for (Edge edge : edges) {
//            newEdges.add(new Edge(edge.getNode1(), edge.getNode2(), edge.getWeight()));
//        }
//
//        for (Structs.Polygon p : polygons) {
//            Integer polyCen = p.getCentroidIdx();
//            double X = aMesh.getVertices(polyCen).getX();
//            double Y = aMesh.getVertices(polyCen).getY();
//            Node polyNode = new Node(polyCen);
//            for(Integer i : p.getNeighborIdxsList()) {
//                Node neighbourNode = new Node(i);
//                double x = aMesh.getVertices(i).getX();
//                double y = aMesh.getVertices(i).getY();
//
//                // Using Pythagoras' Theorem to calculate weight
//                double deltaX = X-x;
//                double deltaY = Y-y;
//                double weight = Math.pow((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)), 0.5);
//                Edge cenEdge = new Edge(polyNode, neighbourNode, weight);
//
//                // Setting edge colour
//                Structs.Polygon polygon = polygons.get(i);
//                Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygon);
//                String color = "207,107,21";
//                new RemoveProperties().remove(pc, "rgb_color");
//                Structs.Property property = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
//
//                Structs.Polygon edgeConverted = pc.addProperties(property).build();
////                polygons.set(i, graphConverted);
//                newEdges.add(cenEdge);
//                polygonList.set(i, edgeConverted);
//            }
//            // Setting node colour
//            Structs.Polygon polygon = polygons.get(polyCen);
//            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(polygon);
//            String color = "207,107,21";
//            new RemoveProperties().remove(pc, "rgb_color");
//            Structs.Property property = Structs.Property.newBuilder().setKey("rgb_color").setValue(color).build();
//
//            Structs.Polygon nodeConverted = pc.addProperties(property).build();
////            polygons.set(polyCen, nodeConverted);
//            polygonList.set(polyCen, nodeConverted);
//        }
//        return polygonList;
//    }
//
//}
