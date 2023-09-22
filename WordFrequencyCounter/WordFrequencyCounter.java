package WordFrequencyCounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /** Prompt the user to enter a text string */
        System.out.println("Enter a text string:");
        String inputText = scanner.nextLine();

        /** Tokenize the input text into words, remove punctuation, and convert to lowercase */
        String[] words = inputText.split("\\s+");
        List<String> stopWords = Arrays.asList("the", "and", "is");
        Map<String, Integer> wordFrequencies = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            /** Remove punctuation and convert to lowercase */
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            /** Check if it's a stop word */
            if (!stopWords.contains(word)) {
                /** Update word frequency */
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                uniqueWords.add(word);
            }
        }

        /** Sort unique words by frequency in descending order */
        List<String> sortedUniqueWords = new ArrayList<>(uniqueWords);
        sortedUniqueWords.sort((w1, w2) -> wordFrequencies.get(w2) - wordFrequencies.get(w1));

        /** Display the word frequency counter */
        System.out.println("\nWord Frequency Counter");
        System.out.println("\nWord\t\tFrequency");
        System.out.println("-------------------------");
        for (String word : sortedUniqueWords) {
            System.out.println(word + "\t\t" + wordFrequencies.get(word));
        }

        scanner.close();
    }
}

