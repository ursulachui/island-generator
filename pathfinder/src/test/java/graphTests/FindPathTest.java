package graphTests;

import graph.FindPath;
import graph.Node;
import graph.NodesAndEdges;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FindPathTest {
    @Test
    public void testCalculateShortestPath() {
        // Creating nodes
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");

        // Creating edges
        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);
        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeE, 15);
        nodeC.addDestination(nodeD, 10);
        nodeC.addDestination(nodeE, 8);
        nodeD.addDestination(nodeE, 5);

        // Test starts from node A
        FindPath<String> findPath = new FindPath<>();
        findPath.calculatePath(nodeA);

        assertEquals(0, nodeA.getDistance());
        assertEquals(10, nodeB.getDistance());
        assertEquals(22, nodeD.getDistance());
        assertEquals(Arrays.asList(nodeA), nodeC.getShortestPath());
        assertEquals(Arrays.asList(nodeA), nodeB.getShortestPath());
        assertEquals(Arrays.asList(nodeA, nodeB), nodeD.getShortestPath());
        assertEquals(Arrays.asList(nodeA, nodeC), nodeE.getShortestPath());
    }
}