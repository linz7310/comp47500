import datastructure.Graph;
import datastructure.Vertex;

public class TestGraph {
    public static void main(String[] args) {
        // Create a directed graph
        Graph directedGraph = new Graph(true);

        // Create vertices
        Vertex v1 = new Vertex(1,1);
        Vertex v2 = new Vertex(2,2);
        Vertex v3 = new Vertex(3,3);
        Vertex v4 = new Vertex(4, 4);

        directedGraph.addVertex(v1);
        directedGraph.addVertex(v2);
        directedGraph.addVertex(v3);
        directedGraph.addVertex(v4);

        // Add edges to the graph
        directedGraph.addEdge(v1, v2, 10);
        directedGraph.addEdge(v1, v3, 20);
        directedGraph.addEdge(v2, v3, 30);
        directedGraph.addEdge(v3, v4, 30);

        System.out.println(directedGraph.toString());

        // Test getInDegree method
        System.out.println("In-Degree of v1: " + directedGraph.getInDegree(v1)); // Output: 0
        System.out.println("In-Degree of v2: " + directedGraph.getInDegree(v2)); // Output: 1
        System.out.println("In-Degree of v3: " + directedGraph.getInDegree(v3)); // Output: 2
        System.out.println("In-Degree of v4: " + directedGraph.getInDegree(v4)); // Output: 1


        // Test getOutDegree method
        System.out.println("Out-Degree of v1: " + directedGraph.getOutDegree(v1)); // Output: 2
        System.out.println("Out-Degree of v2: " + directedGraph.getOutDegree(v2)); // Output: 1
        System.out.println("Out-Degree of v3: " + directedGraph.getOutDegree(v3)); // Output: 1
        System.out.println("Out-Degree of v4: " + directedGraph.getOutDegree(v4)); // Output: 0

        // Test getNumVertices method
        System.out.println("Number of vertices: " + directedGraph.getNumVertex()); // Output: 4

        // Test getNumEdges method
        System.out.println("Number of edges: " + directedGraph.getNumEdges()); // Output: 4

        for(Vertex vertex: directedGraph.getAllVertices()){
            System.out.println("Vertex --- id:" + vertex.getId() + ", value:" + vertex.getValue());
        }

        // Remove an edge from the graph
        directedGraph.removeEdge(v1, v2, 10);

        System.out.println(directedGraph.toString());
        // Test getNumEdges method after removing an edge
        System.out.println("Number of edges after removing one edge: " + directedGraph.getNumEdges()); // Output: 3
        System.out.println("Number of vertices after removing one edge: " + directedGraph.getNumVertex()); // Output: 4

        // Test getInDegree method
        System.out.println("In-Degree of v1: " + directedGraph.getInDegree(v1)); // Output: 0
        System.out.println("In-Degree of v2: " + directedGraph.getInDegree(v2)); // Output: 0
        System.out.println("In-Degree of v3: " + directedGraph.getInDegree(v3)); // Output: 2
        System.out.println("In-Degree of v4: " + directedGraph.getInDegree(v4)); // Output: 1


        // Test getOutDegree method
        System.out.println("Out-Degree of v1: " + directedGraph.getOutDegree(v1)); // Output: 1
        System.out.println("Out-Degree of v2: " + directedGraph.getOutDegree(v2)); // Output: 1
        System.out.println("Out-Degree of v3: " + directedGraph.getOutDegree(v3)); // Output: 1
        System.out.println("Out-Degree of v4: " + directedGraph.getOutDegree(v4)); // Output: 0

        for(Vertex vertex: directedGraph.getAllVertices()){
            System.out.println("After deletion, Vertex --- id:" + vertex.getId() + ", value:" + vertex.getValue());
        }

    }
}
