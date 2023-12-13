package graph;

import java.util.*;

public class Edge {
    public Node n1;
    public Node n2;
    public double weight;

//    public Edge(Node n1, Node n2, int weight, Map<String, Object> elevation, Map<String, Object> city) {
    public Edge(Node n1, Node n2, double weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    public Node getNode1() {
        return n1;
    }
    public Node getNode2() {
        return n2;
    }
    public double getWeight() {
        return weight;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                Objects.equals(n1, edge.n1) &&
                Objects.equals(n2, edge.n2);
    }

    @Override
    public String toString() {
        return n1.getName() + " --" + weight + "--> " + n2.getName();
    }
}
