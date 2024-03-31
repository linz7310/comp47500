package algorithm.priorityqueue;

/**
 * ClassName PriorityMadeByUnSortedSeq
 * Package algorithm.priorityqueue
 * Description:
 *
 * @Author: Lin
 * @Creat: 2024/3/31
 */
public class PriorityMadeByUnSortedSeq<E extends Priority> {
    Priority[] array;
    int size;

    public PriorityMadeByUnSortedSeq(int capacity) {
        array = new Priority[capacity];
    }


    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        array[size++] = e;
        return true;
    }


    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }


    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1,
                    array, index, size - 1 - index);
        }
        array[--size] = null; // help GC
    }


    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        return (E) array[max];
    }


    public boolean isEmpty()  {
        return size == 0;
    }


    public boolean isFull() {
        return size == array.length;
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
        System.out.println("Deleted element: "  + " in " + (endTime - startTime) + " ns");

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
