package AlgorithmPrograms;

import java.util.Scanner;

public class MergeSort {

    // Merge Sort function
    public static void mergeSort(String[] arr, int lo, int hi) {
        if (hi - lo <= 1) {
            return; // Base case: if the sub array length is 0 or 1, it is already sorted
        }

        int mid = lo + (hi - lo) / 2;

        // Recursively sort the two halves
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid, hi);

        // Merge the sorted halves
        merge(arr, lo, mid, hi);
    }

    // Merge function to combine two sorted halves
    public static void merge(String[] arr, int lo, int mid, int hi) {
        // Temporary arrays for the two halves
        String[] left = new String[mid - lo];
        String[] right = new String[hi - mid];

        // Copy data to temp arrays
        System.arraycopy(arr, lo, left, 0, left.length);
        System.arraycopy(arr, mid, right, 0, right.length);

        int i = 0, j = 0, k = lo;

        // Merge the temp arrays back into the original array
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy remaining elements from left (if any)
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy remaining elements from right (if any)
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of strings:");
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] arr = new String[n];
        System.out.println("Enter the strings:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLine();
        }
        mergeSort(arr, 0, arr.length);
        System.out.println("Sorted Strings:");
        for (String str : arr) {
            System.out.println(str);
        }

        scanner.close();
    }
}
