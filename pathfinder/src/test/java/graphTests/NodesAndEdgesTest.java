package graphTests;

import graph.Edge;
import graph.Node;
import graph.NodesAndEdges;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NodesAndEdgesTest {
    @Test
    public void testAddEdge() {
        NodesAndEdges nodesAndEdges = new NodesAndEdges();
        Map<Integer, Node> nodes = new HashMap<>();
        nodes.put(1, new Node(1));
        nodes.put(2, new Node(2));
        nodesAndEdges.nodes = nodes;

        nodesAndEdges.addEdge(1, 2, 10);

        // Both ends of edges added
        assertEquals(1, nodes.get(1).getEdges().size());
        assertEquals(1, nodes.get(2).getEdges().size());

        // Checking edge properties
        Edge edge = (Edge) nodes.get(1).getEdges().get(0);
        assertEquals(nodes.get(1), edge.getNode1());
        assertEquals(nodes.get(2), edge.getNode2());
        assertEquals(10, edge.getWeight());
    }
}