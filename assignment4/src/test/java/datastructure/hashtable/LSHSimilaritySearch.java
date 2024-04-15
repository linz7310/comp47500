package datastructure.hashtable;

import info.debatty.java.lsh.LSHMinHash;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LSHSimilaritySearch {

    public static void main(String[] args) {
        List<boolean[]> res = createVectorFromText();

        int sizeOfVectors = res.get(0).length;
        int numberOfBuckets = 400;
        int stages = 4;

        LSHMinHash lsh = new LSHMinHash(stages, numberOfBuckets, sizeOfVectors);

        int[] firstHash = lsh.hash(res.get(0));
        // search the comment with the most similarity for the first comment
        List<String> textLines = readTextColumnFromCSV("./Reddit_Data.csv", "clean_comment");

        System.out.println("first line comment:" + textLines.get(0));
        int lastIndexOfResult = stages - 1;

        for(int i = 1;i < res.size();i++){
            int[] tmpHash = lsh.hash(res.get(i));
            if(textLines.get(i).replace(" ", "") != ""){
                if(isCloseOrEqual(tmpHash[lastIndexOfResult], firstHash[lastIndexOfResult]) == true){
                    System.out.println(i + "th comment line is similar to the first line");
                    System.out.println(textLines.get(i));
                }
            }
        }

    }

    public static boolean isCloseOrEqual(int secondHash, int thirdHash) {
        return Math.abs(secondHash - thirdHash) == 0;
    }

        // 从CSV文件读取指定列的文本
        private static List<String> readTextColumnFromCSV(String csvFile, String columnName) {
            List<String> textColumn = new ArrayList<>();
            String line;
            try (InputStream inputStream = LSHSimilaritySearch.class.getClassLoader().getResourceAsStream(csvFile)) {
                if (inputStream == null) {
                    throw new IllegalArgumentException("File not found!");
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                int count = 2000;

                // skip the first line
                String metaDataLine = reader.readLine();

                while ((line = reader.readLine()) != null && count > 0) {
                    count--;
                    String[] fields = line.split(",");
                    String comment = fields[0];
                    textColumn.add(comment);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return textColumn;
        }

        // 将文本转换为向量，这里示例的转换逻辑与上面一样
        private static List<boolean[]> createVectorFromText() {
            String csvFile = "./Reddit_Data.csv"; // 输入的CSV文件路径
            List<String> textLines = readTextColumnFromCSV(csvFile, "clean_comment"); // 从CSV文件读取指定列的文本
            List<double[]> vectors = new ArrayList<>();

            // 构建单词词汇表
            List<String> vocabulary = new ArrayList<>();
            for (String str : textLines) {
                String[] words = str.split(" ");
                for (String word : words) {
                    if (!vocabulary.contains(word)) {
                        vocabulary.add(word);
                    }
                }
            }

            // 生成 One-Hot 编码
            List<boolean[]> oneHotVectors = new ArrayList<>();
            for (int i = 0; i < textLines.size(); i++) {
                String[] words = textLines.get(i).split(" ");
                boolean[] oneHotVector = new boolean[vocabulary.size()];
                for (String word : words) {
                    int index = vocabulary.indexOf(word);
                    oneHotVector[index] = true;
                }
                //System.out.println(Arrays.toString(oneHotVector));
                oneHotVectors.add(oneHotVector);
            }

            return oneHotVectors;
    }

    // 将文本转换为向量
    private static double[] createVector(String text) {
        // 在这里实现文本转向量的逻辑，这里只是示例，可以根据实际需求进行更改
        // 这里只是简单地将文本拆分为单词，并将每个单词的出现次数作为向量的值
        String[] words = text.split(" ");
        double[] vector = new double[words.length];
        for (int i = 0; i < words.length; i++) {
            // 假设向量的值为单词出现次数
            vector[i] = countWordOccurrences(text, words[i]);
        }
        return vector;
    }

    private static int countWordOccurrences(String text, String word) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }


}
