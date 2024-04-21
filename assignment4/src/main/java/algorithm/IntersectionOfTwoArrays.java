package algorithm;

/*
  problem: supposed there are two arrays, write a function to compute their intersection.
  solution: This problem is very suitable to be solved with a hash table.
  First, use a hash table to put all nums1 into it, and then traverse the elements of nums2.
  If it exists in the table, it means that it is an intersection part,
  and then insert it into the resulting hash table.
 */

import datastructure.hashtable.HashTable;
import java.util.*;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        // Create a Hash Table to store elements of nums1
        HashTable ht = new HashTable();
        for (int num : nums1) {
            // encode, the index can make the hash code different every time
            // we use key as the hash code, and also the key, the value doesn't matter
            ht.put(num, num, 1);
        }

        // Create a list to store the intersection elements
        HashTable intersectionTable = new HashTable();

        // Traverse nums2 and check for intersection
        for (int num : nums2) {
            // the core logic, we need to compare the hash code and key to make sure if the key is in nums2
            if (ht.get(num, num) != null) {
                intersectionTable.put(num, num, 1);
            }
        }

        // get the final result and return
        List<Object> keys = intersectionTable.getAllUniqueKeys();
        int[] intersectionArray = new int[keys.size()];
        int i = 0;
        for(Object ele: keys){
            intersectionArray[i] = (int) ele;
            i++;
        }

        return intersectionArray;
    }

    // hash code
    public static int hashCode(int num, int tableSize) {
        return num % tableSize;
    }
    public static void main(String[] args) {
        IntersectionOfTwoArrays arrayIntersection = new IntersectionOfTwoArrays();
        int[] nums1 = {1, 2, 3, 3, 4, 5, 6, 7, 10, 1, 2, 1};
        int[] nums2 = {2, 2, 3};

        int[] result = arrayIntersection.intersection(nums1, nums2);
        System.out.println("Intersection: " + Arrays.toString(result)); // result: [2,3]
    }
}
