package AlgorithmPrograms;

import java.util.Scanner;

public class InsertionSortWords {

    public static void insertionSort(String[] words) {
        for (int i = 1; i < words.length; i++) {
            String key = words[i];
            int j = i - 1;

            while (j >= 0 && words[j].compareTo(key) > 0) {
                words[j + 1] = words[j];
                j = j - 1;
            }
            words[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the list of words (comma-separated): ");
        String input = scanner.nextLine();

        String[] words = input.split(",");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        insertionSort(words);

        System.out.println("Sorted List of Words:");
        for (String word : words) {
            System.out.println(word);
        }

        scanner.close();
    }
}

