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
}
