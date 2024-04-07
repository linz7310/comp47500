package Tree.algorithm.binarysearchtree;

import Tree.datastructure.avltree.AVLTree;

import java.util.Random;

public class AVLTreeTest {

    public static void main(String[] args) {
        int[] nodeCounts = {50, 100, 500, 1000, 5000, 10000};

        for (int count : nodeCounts) {
            AVLTree avlTree = new AVLTree();

            long insertTime = 0;
            long deleteTime = 0;

            Random random = new Random();

            for (int i = 0; i < count; i++) {
                int key = random.nextInt(10000);
                long startTime = System.nanoTime();
                avlTree.put(key, "Value");
                long endTime = System.nanoTime();
                insertTime += (endTime - startTime);
            }

            System.out.println("Average insertion time for " + count + " nodes: " + (insertTime / count) + " nanoseconds");

            for (int i = 0; i < count; i++) {
                int key = random.nextInt(10000);
                long startTime = System.nanoTime();
                avlTree.remove(key);
                long endTime = System.nanoTime();
                deleteTime += (endTime - startTime);
            }

            System.out.println("Average deletion time for " + count + " nodes: " + (deleteTime / count) + " nanoseconds");
        }
    }
}

