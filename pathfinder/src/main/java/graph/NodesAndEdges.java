package graph;

import java.util.*;

public class NodesAndEdges {
    public Map<Integer, Node> nodes;

    public void addEdge(int id1, int id2, double weight) {
        Node n1 = nodes.get(id1);
        Node n2 = nodes.get(id2);

        if (n1 != null && n2 != null) {
            Edge edge = new Edge(n1, n2, weight);
            n1.addEdge(edge);
            n2.addEdge(edge);
        }
    }
}
