package AlgorithmPrograms;

import java.io.*;
import java.util.*;

public class WordBinarySearch {
    public static String[] readWordsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();
        if (line != null) {
            String[] words = line.split(",");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].trim();
            }
            return words;
        }
        return new String[0];
    }
    public static boolean binarySearch(String[] words, String target) {
        int low = 0;
        int high = words.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = words[mid].compareTo(target);

            if (comparison == 0) {
                return true;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename with words (comma-separated) or file path :");
        String filename = scanner.nextLine();

        try {
            String[] words = readWordsFromFile(filename);
            Arrays.sort(words);
            System.out.print("Enter the word to search: ");
            String searchWord = scanner.nextLine();
            boolean found = binarySearch(words, searchWord);
            if (found) {
                System.out.println("The word '" + searchWord + "' was found in the list.");
            } else {
                System.out.println("The word '" + searchWord + "' was not found in the list.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        scanner.close();
    }
}

