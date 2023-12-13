package graphTests;

import graph.Node;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    public void testAddDestination() {
        Node<String> node1 = new Node<>("A");
        Node<String> node2 = new Node<>("B");
        node1.addDestination(node2, 10);
        Map<Node<String>, Integer> adjacentNodes = node1.getAdjacentNodes();
        assertEquals(1, adjacentNodes.size());
        assertTrue(adjacentNodes.containsKey(node2));
    }

    @Test
    public void testSetDistance() {
        Node<String> node = new Node<>("A");
        int distance = node.setDistance(5);
        assertEquals(5, distance);
    }

    @Test
    public void testSetShortestPath() {
        Node<String> node1 = new Node<>("A");
        Node<String> node2 = new Node<>("B");
        List<Node<String>> shortestPath = new LinkedList<>();
        shortestPath.add(node1);
        shortestPath.add(node2);
        node1.setShortestPath(shortestPath);
        List<Node<String>> actualPath = node1.getShortestPath();
        assertEquals(2, actualPath.size());
        assertEquals(node1, actualPath.get(0));
    }

}