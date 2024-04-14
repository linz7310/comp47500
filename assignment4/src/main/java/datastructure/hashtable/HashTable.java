package datastructure.hashtable;

/**
 * ClassName HashTable
 * Package datastructure.hashtable
 * Description:
 *
 * @Author: Lin
 * @Creat: 2024/4/10
 */
public class HashTable {
    static class Entry {
        int hash; // hash code
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size = 0; // number of elements
    float loadFactor = 0.75f;
    int threshold = (int) (loadFactor * table.length);

    /* Replacing modulo operation with bitwise operation
   - Assumption: The array length is a power of 2
   - hash % array length is equivalent to hash & (array length - 1)
*/


    //Get value based on hash code
    Object get(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    //    put a new key value in hash table if the key exist update it
    void put(int hash, Object key, Object value) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            //1. if idx = null just add it
            table[idx] = new Entry(hash, key, value);
        } else {
            // idx != null if there is a duplicate key, update it, or add a new one.
            Entry p = table[idx];
            while (true) {
                if (p.key.equals(key)) {
                    p.value = value; // update
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash, key, value); // add new one
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    //    Delete according to the hash code, return the deleted value.
    Object remove(int hash, Object key) {
        int idx = hash & (table.length - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry p = table[idx];
        Entry prev = null;
        while (p != null) {
            if (p.key.equals(key)) {
                // find it and delete
                if (prev == null) {
                    table[idx] = p.next;
                } else {
                    prev.next = p.next;
                }
                size--;
                return p.value;
            }
        }
        return null;
    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i]; // 拿到每个链表头
            if (p != null) {

                Entry a = null;
                Entry b = null;
                Entry aHead = null;
                Entry bHead = null;
                while (p != null) {
                    if ((p.hash & table.length) == 0) {
                        if (a != null) {
                            a.next = p;
                        } else {
                            aHead = p;
                        }
                        a = p; //add to a
                    } else {
                        if (b != null) {
                            b.next = p;
                        } else {
                            bHead = p;
                        }
                        b = p; //add to b
                    }
                    p = p.next;
                }
                // Law: a linked table keeps index position, b linked table index position + table.length
                if (a != null) {
                    a.next = null;
                    newTable[i] = aHead;
                }
                if (b != null) {
                    b.next = null;
                    newTable[i + table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

}
