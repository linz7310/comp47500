package algorithm;

import datastructure.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Problem: supposed there is an array of strings words, return the words that can be typed using letters of the alphabet on only one row of American keyboard

    Solution: The key to this problem is to encode the keyboard information into the hash table.
    We can save the three lines of keyboard characters into three tables, and then traverse
    each character in each word to see whether the current character is in any of the three sets.
    Specifically, if the set in which the first character of a word exists is determined,
    it is necessary to continue to determine whether other characters are also in this set.
    If they are all present, the word can meet the requirements, otherwise it does not.
 */
public class KeyRowsEncoding {
    public static String[] findWords(String[] words) {
        String[] keyboardRows = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        HashTable ht = new HashTable();

        // Populate ht with character-row index mappings
        for (int i = 0; i < keyboardRows.length; i++) {
            String row = keyboardRows[i];
            for (int j = 0; j < row.length(); j++) {
                char letter = Character.toLowerCase(row.charAt(j));
                // Map character to its hash table
                // we encoded the character into one hash table, but for each line, their values are different
                // because their value is different, so we can use it to judge if the characters of the word can
                // get the same value
                ht.put(letter - '0', letter, i);
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (word.isEmpty()) continue; // Skip empty words

            boolean isValid = true;
            char firstLetter = Character.toLowerCase(word.charAt(0));
            int rowIdx = (int) ht.get(firstLetter - '0', firstLetter); // Get value of first character

            for (int i = 1; i < word.length(); i++) {
                char otherLetter = Character.toLowerCase(word.charAt(i));
                Object hashKey = ht.get(otherLetter - '0', otherLetter);
                if (hashKey != null && (int) hashKey!= rowIdx) {
                    isValid = false; // If character has the different value, word is invalid
                    break;
                }
            }
            if (isValid) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] words = {"Hello", "Alaska", "Dad", "Peace", "beautiful"};
        String[] result = findWords(words);
        System.out.println("These are the words:");
        for(String word: words){
            System.out.print(word + '\t');
        }
        System.out.println("\nWords that can be typed using letters of the alphabet on only one row: ");
        for (String word : result) {
            System.out.println(word);
        }
    }
}
