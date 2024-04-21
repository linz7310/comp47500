package datastructure.hashtable;

import info.debatty.java.lsh.LSHMinHash;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// we noticed that LSH(locality sensitive hashing) is very effective and useful ,so we wrote a test to apply it on
// the reddit comment data, to find out the similarities among the comments
// minHash: https://en.wikipedia.org/wiki/MinHash
public class LSHSimilaritySearch {

    public static void main(String[] args) {
        List<boolean[]> res = createVectorFromText();

        // the length of each vector
        int sizeOfVectors = res.get(0).length;
        // the hashing bucket
        int numberOfBuckets = 400;
        // this parameter means hashing four times, the last time will be the final hashing bucket
        // we use this one to judge whether two comments are similar or not
        int stages = 4;

        // the lsh algorithm
        LSHMinHash lsh = new LSHMinHash(stages, numberOfBuckets, sizeOfVectors);

        // the hashing result of the first comment
        int[] firstHash = lsh.hash(res.get(0));
        // search the comment with the most similarity for the first comment
        List<String> textLines = readTextColumnFromCSV("./Reddit_Data.csv", "clean_comment");

        System.out.println("first line comment:" + textLines.get(0));
        int lastIndexOfResult = stages - 1;

        for(int i = 1;i < res.size();i++){
            int[] tmpHash = lsh.hash(res.get(i));
            if(textLines.get(i).replace(" ", "") != ""){
                // if the hashing bucket is equal to the bucket of first comment, then print the result
                if(isCloseOrEqual(tmpHash[lastIndexOfResult], firstHash[lastIndexOfResult]) == true){
                    System.out.println(i + "th comment line is similar to the first line");
                    System.out.println("the exact comment of this line: " + textLines.get(i));
                }
            }
        }

    }

    public static boolean isCloseOrEqual(int secondHash, int thirdHash) {
        return Math.abs(secondHash - thirdHash) == 0;
    }

    // read the comment data into List<String>, every line is the comment from user
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

    // read the comment file ,and then turn them into one hot vector arrays
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

        // generate one hot encoding
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

}
