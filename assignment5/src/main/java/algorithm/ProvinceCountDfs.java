package algorithm;

import datastructure.Edge;
import datastructure.Graph;
import datastructure.Vertex;

import java.util.*;

public class ProvinceCountDfs {

    public static List<List<Vertex>> findProvinces(Graph graph) {
        List<List<Vertex>> provinces = new ArrayList<>();
        Set<Vertex> visitedVertices = new HashSet<>();
        for (Vertex vertex : graph.getAllVertices()) {
            if (!visitedVertices.contains(vertex)) {
                List<Vertex> province = new ArrayList<>();
                dfs(graph, vertex, visitedVertices, province);
                provinces.add(province);
            }
        }
        return provinces;
    }

    private static void dfs(Graph graph, Vertex currentVertex, Set<Vertex> visitedVertices, List<Vertex> province) {
        visitedVertices.add(currentVertex);
        province.add(currentVertex); // 将当前顶点加入省份列表中
        for (Edge edge : graph.getAdjacencyMap().getOrDefault(currentVertex, new ArrayList<>())) {
            Vertex neighbor = edge.getDestination();
            if (!visitedVertices.contains(neighbor)) {
                dfs(graph, neighbor, visitedVertices, province);
            }
        }
    }

    public static void main(String[] args) {
//        smallTest();
        final int ITERATIONS = 5;
        for (int N = 0; N <= 500000; N += 500) {
            Graph graph = generateRandomGraph(N);

            long timingDFS = 0;
            List<List<Vertex>> provinces = new ArrayList<>();
            for (int it = 0; it < ITERATIONS; it++) {
                long start = System.currentTimeMillis();
                provinces = findProvinces(graph);
                long end = System.currentTimeMillis();
                timingDFS += end - start;
            }
            timingDFS = timingDFS / ITERATIONS;

//            System.out.println("Number of provinces: " + provinces.size());
//            for (int i = 0; i < provinces.size(); i++) {
//                System.out.println("Province " + (i + 1) + ": " + provinces.get(i));
//            }
            System.out.println(timingDFS);
        }
    }

    private static Graph generateRandomGraph(int numVertices) {
        Random generator = new Random();
        Graph graph = new Graph(false);
        List<Vertex> vertices = new ArrayList<>();
        // 创建顶点
        for (int i = 0; i < numVertices; i++) {
            Vertex ver = new Vertex(i, i);
            vertices.add(ver);
            graph.addVertex(ver);
        }

        Set<Integer> generated = new HashSet<>();
        while (generated.size() < numVertices * 1.2) {
            int value1 = generator.nextInt(numVertices); // Randomly generate the first vertex value
            int value2 = generator.nextInt(numVertices); // Randomly generate the second vertex value

            // 如果生成的两个顶点值相同或已经生成过该边，则重新生成
            while (value1 == value2 || generated.contains(value1 * numVertices + value2) || generated.contains(value2 * numVertices + value1)) {
                value1 = generator.nextInt(numVertices);
                value2 = generator.nextInt(numVertices);
            }

            Vertex vertex1 = graph.getVertex(value1);
            Vertex vertex2 = graph.getVertex(value2);

            graph.addEdge(vertex1, vertex2, 1); // 添加边

            // 记录已经生成过的边
            generated.add(value1 * numVertices + value2);
            generated.add(value2 * numVertices + value1);
        }
        return graph;
    }

    public static void smallTest() {
        // 创建一个 Graph 对象并添加边
        Graph graph = new Graph(false);
        Vertex v1 = new Vertex(1,1);
        Vertex v2 = new Vertex(2,2);
        Vertex v3 = new Vertex(3,3);
        Vertex v4 = new Vertex(4,4);
        Vertex v5 = new Vertex(5,5);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v2, v3, 1);
        graph.addEdge(v4, v5, 1);

        // 调用省份查找方法并输出结果
        List<List<Vertex>> provinces = findProvinces(graph);
        System.out.println("Number of provinces: " + provinces.size());
        for (int i = 0; i < provinces.size(); i++) {
            System.out.print("Province " + (i + 1) + ": ");
            for(Vertex vertex: provinces.get(i)){
                System.out.print(vertex.getValue() + ",");
            }
            System.out.println();
        }
    }
}
