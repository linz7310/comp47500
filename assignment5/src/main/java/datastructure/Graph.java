package datastructure;

import java.util.*;

// direct graph: A -> B
// indirect graph: A - B, when storing, we need to store two edges, A -> B, B -> A, so when we calculate the
// edges, we need to consider this
public class Graph {
    // using map to store the edges and the relation between vertex and edges
    // key is the vertex, value is the edges whose source is the vertex
    private Map<Vertex, List<Edge>> adjacencyMap;  // store all the edges
    private Set<Vertex> vertices;   // store all vertices

    boolean isDirected = true; // is direct graph in default

    public Graph(boolean isDirected) {
        this.adjacencyMap = new HashMap<>();
        this.vertices = new HashSet<>();
        this.isDirected = isDirected;
    }

    public Map<Vertex, List<Edge>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public Vertex getVertex(int value) {
        for (Vertex vertex : vertices) {
            if (vertex.getValue() == value) {
                return vertex;
            }
        }
        return null;
    }

    // To remove all outgoing and incoming edges of this node
    public void removeVertex(Vertex vertex) {
        for (List<Edge> edges : adjacencyMap.values()) {
            for(Edge edge: edges){
                if(edge.destination == vertex){
                    Vertex source = edge.source;
                    edges.remove(edge);
                    source.outDegreeMinusOne();
                }else if(edge.source == vertex){
                    Vertex destination = edge.destination;
                    edges.remove(edge);
                    destination.inDegreeMinusOne();
                }
            }
        }
        adjacencyMap.remove(vertex);
        vertices.remove(vertex);
    }

    public void addEdge(Vertex source, Vertex destination, int weight) {
        adjacencyMap.putIfAbsent(source, new ArrayList<>());
        this.adjacencyMap.get(source).add(new Edge(source, destination, weight));
        source.outDegreePlusOne();
        destination.inDegreePlusOne();

        // If it is an undirected graph, we also need to add reverse edges
        if(!this.isDirected){
            adjacencyMap.putIfAbsent(destination, new ArrayList<>());
            this.adjacencyMap.get(destination).add(new Edge(destination, source, weight));
            source.inDegreePlusOne();
            destination.outDegreePlusOne();
        }
    }

    public void removeEdge(Vertex source, Vertex destination, int weight) {
        for (Map.Entry<Vertex, List<Edge>> entry : adjacencyMap.entrySet()) {
            Vertex key = entry.getKey();
            List<Edge> edges = entry.getValue();
            if (key.equals(source)) {
                List<Edge> newEdges = List.copyOf(edges);
                for(Edge edge: newEdges){
                    if(edge.getDestination().equals(destination) && edge.weight == weight){
                        edges.remove(edge);
                        key.outDegreeMinusOne();  // we also need to recalculate the degree
                        edge.getDestination().inDegreeMinusOne();
                    }
                }
            }
            // If it is an undirected graph, we also need to delete the reverse edges.
            if (!this.isDirected && key.equals(destination)) {
                List<Edge> newEdges = List.copyOf(edges);
                for(Edge edge: newEdges){
                    if(edge.getDestination().equals(source) && edge.weight == weight){
                        edges.remove(edge);
                        key.inDegreeMinusOne();
                        edge.getDestination().outDegreeMinusOne();
                    }
                }
            }
        }
    }

    public int getInDegree(Vertex vertex) {
        return vertex.getInDegree();

    }

    public int getOutDegree(Vertex vertex) {
        return vertex.getOutDegree();
    }

    public int getNumVertex() {
        return vertices.size();
    }

    public List<Vertex> getAllVertices() {
        return vertices.stream().toList();
    }

    public int getNumEdges() {
        int numEdges = 0;
        for (Map.Entry<Vertex, List<Edge>> entry : adjacencyMap.entrySet()) {
            numEdges += entry.getValue().size();
        }
        if(isDirected)
            return numEdges;
        else
            return numEdges / 2;  // if it is indirect graph, we need to do division
    }

    // print the graph
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String verStr = "";
        for(Vertex vertex: vertices)
            verStr += vertex.getValue() + ",";
        sb.append("vertices are:" + verStr + "\n");
        for (Map.Entry<Vertex, List<Edge>> entry : this.adjacencyMap.entrySet()) {
            List<Edge> edges = entry.getValue();
            for (Edge edge : edges) {
                sb.append(edge.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}