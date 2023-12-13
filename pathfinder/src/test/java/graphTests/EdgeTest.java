package graphTests;

import graph.Edge;
import graph.Node;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    @Test
    public void edgeConstructor() {
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        double weight = 10;
        Edge e = new Edge(n1, n2, weight);
        assertEquals(n1, e.n1);
        assertEquals(n2, e.n2);
        assertEquals(weight, e.weight);
    }

    @Test
    public void edgeToString() {
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        double weight = 10;
        Edge e = new Edge(n1, n2, weight);
        String expected = "A --10.0--> B";
        assertEquals(expected, e.toString());
    }

    @Test
    public void edgeEquals() {
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        double weight = 10;
        Edge e1 = new Edge(n1, n2, weight);
        Edge e2 = new Edge(n1, n2, weight);
        Edge e3 = new Edge(n2, n1, weight);
        assertEquals(e1, e2);
        assertNotEquals(e1, e3);
    }
}