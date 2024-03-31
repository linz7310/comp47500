package datastructure;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName Heap
 * Package datastructure
 * Description:
 * Expandable heap, max is used to specify whether it is a big top heap or a small top heap.
 * @Author: Lin
 * @Creat: 2024/3/30
 */
public class Heap {
    int[] array;
    int size;
    boolean max;
    public int size() {
        return size;
    }

    public Heap(int capacity, boolean max){
        this.array = new int[capacity];
        this.max = max;
    }
    public Heap(int[] array, boolean max) {
        this.array = array;
        this.size = array.length;
        this.max = max;
        heapify();
    }


    /**
     * Get the top element of the heap
     * @return top element of the heap
     */
    public int peek(){
        return array[0];
    }

    /**
     * delete the top element
     * @return the top element
     */
    public int poll(){
        int top = array[0];
        swap(0,size-1);
        size--;
        down(0);
        return top;
    }

    /**
     * delete element by index
     * @param index
     * @return element deleted
     */
    public int poll(int index){
        int deleted = array[index];
        up(Integer.MAX_VALUE, index);
        poll();
        return deleted;
    }

    /**
     * replace top element
     * @param replaced new element
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * add element to the end
     * @param offered new element
     * @return if added
     */
    public void offer(int offered) {
        if (size == array.length) {
            // 扩容
            grow();
        }
        up(offered, size);
        size++;
    }

    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0,
                newArray, 0, size);
        array = newArray;
    }

    // 建堆
    private void heapify() {
        // 如何找到最后这个非叶子节点  size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }
    public void swap(int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void up(int offered, int index) {
        int child = index;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }



    public void down(int parent){
        int left = parent*2+1;
        int right = left +1;
        int maxOrmin = parent;
        if (left < size && (max ? array[left]> array[maxOrmin] : array[left] < array[maxOrmin]) ){
            maxOrmin = left;
        }
        if (right < size && (max ? array[right]> array[maxOrmin] : array[right] < array[maxOrmin] )){
            maxOrmin = right;
        }
        if (maxOrmin != parent){
            swap(maxOrmin,parent);
            down(maxOrmin);
        }
    }
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

}
