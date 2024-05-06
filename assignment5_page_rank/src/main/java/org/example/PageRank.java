package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Graph;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.List;

// dataset link: https://www.kaggle.com/datasets/mathurinache/twitter-edge-nodes/data
// because the data is so large, we can't upload to github, so we just provide the link here.

public class PageRank {
    public static void main(String[] args) {
        // Set up Spark configuration
        SparkConf conf = new SparkConf().setAppName("PageRankSpark").setMaster("local[1]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Define iterations
        int[] iterations = {2000000, 4000000, 6000000, 8000000, 10000000};

        // Read the file and compute PageRank for different number of lines
        for (int numLines : iterations) {
            // Read file content
            JavaRDD<String> lines = sc.textFile("/Users/pangmingyu/Downloads/Twitter-dataset/data/edges2.csv")
                    .sample(false, (double) numLines / 10000000.0); // Sample based on the number of lines

            // Convert sampled lines to edges RDD
            JavaRDD<Edge<Integer>> edges = lines.map(line -> {
                String[] parts = line.split(",");
                return new Edge<>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), null);
            });

            // Build graph from edges RDD
            Graph graph = Graph.fromEdges(edges.rdd(), 1, StorageLevel.MEMORY_AND_DISK_SER(), // edge level
                    StorageLevel.MEMORY_AND_DISK_SER(), // vertex level
                    scala.reflect.ClassTag$.MODULE$.apply(Integer.class), // edge ClassTag
                    scala.reflect.ClassTag$.MODULE$.apply(Integer.class));

            // Run PageRank algorithm and record time
            long startTime = System.currentTimeMillis();
            Graph<Object, Object> ranks = org.apache.spark.graphx.lib.PageRank.runUntilConvergence(graph, 0.001, 0.85, null, null);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            // Print the time for each run
            System.out.println("PageRank algorithm converged in " + elapsedTime + " milliseconds for " + numLines + " lines.");
        }


//        List<Tuple2<Object, Double>> top10Nodes = pageRankRDD.sortByKey(false)
//                .take(10);
//        System.out.println("Top 10 nodes after convergence:");
//        for (Tuple2<Object, Double> tuple : top10Nodes) {
//            System.out.println("Node: " + tuple._1() + ", PageRank: " + tuple._2());
//        }

        sc.stop();
    }

    // 比较器用于对 PageRank 结果排序
    static class TupleComparator implements java.util.Comparator<Tuple2<Object, Double>> {
        public int compare(Tuple2<Object, Double> tuple1, Tuple2<Object, Double> tuple2) {
            return -Double.compare(tuple1._2(), tuple2._2()); // 降序排列
        }
    }
}

