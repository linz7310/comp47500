package datastructure.hashtable;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTableBigDataTest {
    public static void main(String[] args) {
        String csvFile = "/Users/pangmingyu/IdeaProjects/comp47500/assignment4/src/main/resources/ecommerce_consumer_behaviour_small.csv";
                String line;
        String csvSplitBy = ",";
        HashTable ht = new HashTable();
        int totalElemnts = 0;  // Total number of lines read and Elemnts added

        long startTime = System.currentTimeMillis();  // Start time for inserts

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine(); // Skip the first line (headers)

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);
                if (fields.length > 0) {
                    int orderId = Integer.parseInt(fields[0]);  // we use orderId to do the hash test
                    ht.put(orderId % 1000, orderId, line);  // store the ids
                    totalElemnts++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + csvFile);
            return;
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
            return;
        }

        long endTime = System.currentTimeMillis();  // End time for inserts
        long insertTime = endTime - startTime;  // Total time for inserts
        System.out.println("Total orderIds added: " + totalElemnts);
        System.out.println("Insertion took: " + insertTime + " ms");
        int uniqueKeys = ht.countUniqueKeys();
        System.out.println("Total unique orderIds: " + uniqueKeys);


        // Deleting half of the Elemnts
        int deleteCount = 500;  // Number of Elemnts to delete
        int deletedElements = 0;
        startTime = System.currentTimeMillis();  // Reset start time for deletions

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine();  // Skip headers again

            while ((line = reader.readLine()) != null && deletedElements < deleteCount) {
                String[] fields = line.split(csvSplitBy);
                if (fields.length > 0) {
                    int key = Integer.parseInt(fields[0]);
                    if (ht.remove(key % 1000, key) != null) {
                        deletedElements++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + csvFile);
        } catch (IOException e) {
            System.out.println("An I/O error occurred.");
        }

        endTime = System.currentTimeMillis();  // End time for deletions
        long deleteTime = endTime - startTime;  // Total time for deletions
        System.out.println("Deleted " + deletedElements + " Elemnts out of requested " + deleteCount);
        System.out.println("Deletion took: " + deleteTime + " ms");
        int uniqueKeys2 = ht.countUniqueKeys();
        System.out.println("Total unique keys: " + uniqueKeys2);
    }



//    @Test
//    public void testPutAndGet() {
//        HashTable ht = new HashTable();
//        ht.put("key1".hashCode(), "key1", "value1");
//        assertEquals("value1", ht.get("key1".hashCode(), "key1"));
//    }
}
