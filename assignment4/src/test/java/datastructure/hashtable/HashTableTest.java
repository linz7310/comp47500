package datastructure.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName HashTableTest
 * Package datastructure.hashtable
 * Description:
 *
 * @Author: Lin
 * @Creat: 2024/4/14
 */
public class HashTableTest {
    @Test
    void testPutAndGet() {
        HashTable ht = new HashTable();
        ht.put(1, "key1", "value1");
        ht.put(2, "key2", "value2");
        ht.put(3, "key3", "value3");

        assertEquals("value1", ht.get(1, "key1"));
        assertEquals("value2", ht.get(2, "key2"));
        assertEquals("value3", ht.get(3, "key3"));
        assertNull(ht.get(4, "key4"));  // Test for non-existent keys
    }

    @Test
    void testUpdateValue() {
        HashTable ht = new HashTable();
        ht.put(1, "key1", "value1");
        ht.put(1, "key1", "newValue1");  // Update the value of the key "key1".

        assertEquals("newValue1", ht.get(1, "key1"));
    }

    @Test
    void testRemove() {
        HashTable ht = new HashTable();
        ht.put(1, "key1", "value1");
        ht.put(2, "key2", "value2");

        assertEquals("value1", ht.remove(1, "key1"));
        assertNull(ht.get(1, "key1"));  //  Confirmation that the data has been deleted
        assertNotNull(ht.get(2, "key2"));  // Confirmation that other data are not affected
    }

    @Test
    void testResize() {
        HashTable ht = new HashTable();
        // Fill the hash table to trigger resizing
        for (int i = 0; i < 50; i++) {
            ht.put(i, "key" + i, "value" + i);
        }

        // Verify data integrity after resizing
        assertEquals("value25", ht.get(25, "key25"));
        assertEquals(50, ht.size);  // Confirmation of the correct amount of data
    }
}
