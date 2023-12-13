package graph;

import java.util.*;

//code adapted from: https://github.com/geekific-official/geekific-youtube/blob/main/graph-dijkstra/src/main/java/com/youtube/geekific/Node.java
public class Node<T> implements Comparable<Node<T>> {

    private T id;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node<T>> shortestPath = new LinkedList<>();
    private Map<Node<T>, Integer> adjacentNodes = new HashMap<>();
    public List<Edge> edges;

    public Node(T id) {
        this.id = id;
        this.edges = new ArrayList<Edge>();
    }

    public Map<Node<T>, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public T getName() {
        return id;
    }

    public Integer getDistance() {
        return distance;
    }
    public List<Edge> getEdges() {
        return edges;
    }
    public List<Node<T>> getShortestPath() {
        return shortestPath;
    }

    public Integer setDistance(Integer distance) {
        this.distance = distance;
        return distance;
    }
    public List<Node<T>> setShortestPath(List<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
        return shortestPath;
    }
    public void addDestination(Node<T> destination, int weight) {
        adjacentNodes.put(destination, weight);
    }
    public void addAdjacentNode(Node<T> node, int weight) {
        adjacentNodes.put(node, weight);
    }
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }

}