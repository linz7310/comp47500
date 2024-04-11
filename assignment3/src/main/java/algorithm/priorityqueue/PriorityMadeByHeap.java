package algorithm.priorityqueue;

import datastructure.Heap;




/**
 * ClassName PriorityMadeByHeap
 * Package algorithm.priorityqueue
 * Description: made by Maxheap
 * @Author: Lin
 * @Creat: 2024/3/30
 */
public class PriorityMadeByHeap<E extends Priority>{

    private Heap heap;

    public PriorityMadeByHeap(int capacity, boolean max) {
        this.heap = new Heap(capacity, max);
    }

    public boolean offer(E element) {
        if (heap.isFull()) {
            return false;
        }
        heap.offer(element.priority());
        return true;
    }
    public int poll() {
        if (heap.size() == 0) {
            return -1;
        }
        return heap.poll();
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return heap.peek();
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }


    public boolean isFull() {
        return heap.isFull();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {

        long startTime, endTime;
        PriorityMadeByHeap<Entry> priorityQueue = new PriorityMadeByHeap<>(5, true);

        // add elements
        startTime = System.nanoTime();
        priorityQueue.offer(new Entry("A", 10));
        priorityQueue.offer(new Entry("B", 20));
        priorityQueue.offer(new Entry("C", 5));
        priorityQueue.offer(new Entry("D", 15));
        priorityQueue.offer(new Entry("E", 25));
        endTime = System.nanoTime();
        System.out.println("Time to offer: " + (endTime - startTime) + " ns");

        // show the queue
        System.out.println("Queue after adding elements:");
        System.out.println(priorityQueue.toString());

        // delete element
        startTime = System.nanoTime();
        int deleted = priorityQueue.poll();
        System.out.println("Deleted element: " + deleted);
        endTime = System.nanoTime();
        System.out.println("Deleted element: " + " in " + (endTime - startTime) + " ns");

        deleted = priorityQueue.poll();
        System.out.println("Deleted element: " + deleted);

        // check the queue after deleting
        System.out.println("Queue after removing elements:");
        System.out.println(priorityQueue.toString());


        System.out.println("Peek: " + priorityQueue.peek());


        System.out.println("Is empty: " + priorityQueue.isEmpty());
        System.out.println("Is full: " + priorityQueue.isFull());
    }
}
