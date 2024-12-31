package AlgorithmPrograms;

import java.util.*;

public class GenericSearchSort {

    public static <T extends Comparable<T>> boolean binarySearch(List<T> list, T target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).compareTo(target);
            if (comparison == 0) return true;
            if (comparison < 0) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a list of words separated by spaces:");
        String input = scanner.nextLine();
        List<String> words = new ArrayList<>(Arrays.asList(input.split("\\s+")));

        System.out.println("Enter the word to search for:");
        String target = scanner.nextLine();

        boolean found = binarySearch(words, target);
        System.out.println("Binary Search result for '" + target + "': " + (found ? "Found" : "Not Found"));

        insertionSort(words);
        System.out.println("Sorted List: " + words);

        scanner.close();
    }
}
