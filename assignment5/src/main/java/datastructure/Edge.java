package datastructure;

public class Edge {
    Vertex source;
    Vertex destination;
    int weight;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Getters and setters
    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Edge from " + source.getValue() + " to " + destination.getValue() + ", weight: " + weight;
    }
}