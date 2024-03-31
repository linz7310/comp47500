package algorithm.priorityqueue;

/**
 * ClassName PriorityMadeBySortedSeq
 * Package algorithm.priorityqueue
 * Description:
 *
 * @Author: Lin
 * @Creat: 2024/3/31
 */
public class PriorityMadeBySortedSeq<E extends Priority> {
    Priority[] array;
    int size;

    public PriorityMadeBySortedSeq(int capacity) {
        array = new Priority[capacity];
    }


    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        insert(e);
        size++;
        return true;
    }


    private void insert(E e) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = e;
    }



    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        array[--size] = null; // help GC
        return e;
    }


    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
    }


    public boolean isEmpty() {
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
