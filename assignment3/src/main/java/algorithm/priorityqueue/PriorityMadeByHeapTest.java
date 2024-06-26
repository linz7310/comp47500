package algorithm.priorityqueue;

import java.util.Random;

public class PriorityMadeByHeapTest {

    public static void main(String[] args) {
        long startTime, endTime;
        // testing procedure
        int[] numbersOfEleArr = new int[]{50, 500, 5000, 50000, 500000, 5000000};

        for(int ele: numbersOfEleArr){
            testWithNumbers(ele);
        }
    }

    public static void testWithNumbers(int count){
        long startTime, endTime;
        PriorityMadeByHeap<Entry> priorityQueue = new PriorityMadeByHeap<>(count+1, true);
        // add elements
        Random random = new Random();
        startTime = System.nanoTime();
        for(int i = 0; i < count; i++){
            String randomStr = String.valueOf(random.nextInt(10000000 ) + 1);
            int randomNumber = random.nextInt(1000000 ) + 1;
            priorityQueue.offer(new Entry(randomStr, randomNumber));
        }
        endTime = System.nanoTime();
        System.out.println("Time to build the PriorityQueue with " + count + " elements: " + (endTime - startTime) + " ns");

        // delete element
        startTime = System.nanoTime();
        for(int i = 0; i < count; i++) {
            int deleted = priorityQueue.poll();
        }
        endTime = System.nanoTime();
        System.out.println("Deleted element: " + " in " + (endTime - startTime) + " ns");

        System.out.println("Is empty: " + priorityQueue.isEmpty());
        System.out.println("Is full: " + priorityQueue.isFull());
    }
}
