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
            // 移动
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


    public boolean isEmpty() {
        return size == 0;
    }


    public boolean isFull() {
        return size == array.length;
    }
}
