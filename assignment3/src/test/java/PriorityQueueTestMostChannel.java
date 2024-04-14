import algorithm.priorityqueue.Entry;
import algorithm.priorityqueue.PriorityMadeByHeap;

import java.io.*;
import java.util.Random;

public class PriorityQueueTestMostChannel {
    public static void main(String[] args) throws IOException {
        long startTime = 0, endTime = 0;

        PriorityMadeByHeap<Entry> priorityQueue = new PriorityMadeByHeap<>(1000, true);

        String csvFile = "./Most_Subscribed_YouTube_Channels_exported.csv";
        String line;
        String csvSplitBy = ",";

        try (InputStream inputStream = PriorityQueueTestMostChannel.class.getClassLoader().getResourceAsStream(csvFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found!");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // skip the first line
            String metaDataLine = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                double subscribers = Double.parseDouble(fields[2].replaceAll("\"", ""));
                priorityQueue.offer(new Entry(name, (int) subscribers * 1000000));
            }
            // add elements
            endTime = System.nanoTime();

            // delete element
            for (int i = 0; i < 4; i++) {
                System.out.println("Peek element: " + priorityQueue.peek());
                int deleted = priorityQueue.poll();
                System.out.println("Deleted element: " + deleted);
            }
            endTime = System.nanoTime();
            System.out.println("Deleted element: " + " in " + (endTime - startTime) + " ns");

            System.out.println("Is empty: " + priorityQueue.isEmpty());
            System.out.println("Is full: " + priorityQueue.isFull());
        }
    }
}
