package datastructure.hashtable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HashTableBigDataTest {
    public static void main(String[] args) {
        String csvFile = "./ECommerce_consumer _behaviour.csv.csv";
        String line;
        String csvSplitBy = ",";
        HashTable ht = new HashTable();

//        try (InputStream inputStream = HashTableBigDataTest.class.getClassLoader().getResourceAsStream(csvFile)) {
//            if (inputStream == null) {
//                throw new IllegalArgumentException("File not found!");
//            }
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            // skip the first line
//            String metaDataLine = reader.readLine();
//
//            while ((line = reader.readLine()) != null) {
//                String[] fields = line.split(",");
//                String name = fields[0];
//                ht.put(i);
//            }
    }
}
