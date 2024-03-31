package algorithm.priorityqueue;

import datastructure.Heap;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * ClassName PriorityMadeByHeap
 * Package algorithm.priorityqueue
 * Description:
 * mad by Maxheap
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
            // 队列已满，无法添加
            return false;
        }
        heap.offer(element.priority());
        return true;
    }

    /**
     * 从优先队列中移除并返回优先级最高（或最低，取决于是最大堆还是最小堆）的元素。
     *
     * @return 优先级最高的元素的优先级值；如果队列为空，则返回-1或其他标记值。
     */
    public int poll() {
        if (heap.size() == 0) {
            // 队列为空
            return -1; // 或抛出异常
        }
        return heap.poll();
    }

    /**
     * 获取但不移除优先队列中优先级最高的元素。
     *
     * @return 优先级最高的元素的优先级值；如果队列为空，则返回-1或其他标记值。
     */
    public int peek() {
        if (heap.size() == 0) {
            // 队列为空
            return -1; // 或抛出异常
        }
        return heap.peek();
    }

    /**
     * 检查优先队列是否为空。
     *
     * @return 如果队列为空，则返回true；否则返回false。
     */
    public boolean isEmpty() {
        return heap.size() == 0;
    }

    /**
     * 检查优先队列是否已满。
     *
     * @return 如果队列已满，则返回true；否则返回false。
     */
    public boolean isFull() {
        return heap.isFull();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    public static void main(String[] args) {
        // 创建 PriorityMadeByHeap 对象
        PriorityMadeByHeap<Entry> priorityQueue = new PriorityMadeByHeap<>(5, true);

        // 添加元素
        priorityQueue.offer(new Entry("A", 10));
        priorityQueue.offer(new Entry("B", 20));
        priorityQueue.offer(new Entry("C", 5));
        priorityQueue.offer(new Entry("D", 15));
        priorityQueue.offer(new Entry("E", 25));

        // 输出当前队列状态
        System.out.println("Queue after adding elements:");
        System.out.println(priorityQueue.toString());

        // 删除元素
        int deleted = priorityQueue.poll();
        System.out.println("Deleted element: " + deleted);

        deleted = priorityQueue.poll();
        System.out.println("Deleted element: " + deleted);

        // 输出删除元素后的队列状态
        System.out.println("Queue after removing elements:");
        System.out.println(priorityQueue.toString());

        // 查看堆顶元素
        System.out.println("Peek: " + priorityQueue.peek());

        // 检查队列是否为空和已满
        System.out.println("Is empty: " + priorityQueue.isEmpty());
        System.out.println("Is full: " + priorityQueue.isFull());
    }
}
