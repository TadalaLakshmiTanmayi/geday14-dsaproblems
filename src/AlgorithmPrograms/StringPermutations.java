package AlgorithmPrograms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StringPermutations {
    public static List<String> iterativePermutations(String str) {
        List<String> result = new ArrayList<>();
        int n = str.length();
        int[] indices = new int[n];
        char[] characters = str.toCharArray();
        Arrays.sort(characters);
        result.add(new String(characters));

        for (int i = 0; i < n; ) {
            if (indices[i] < i) {
                int j = ((i % 2) == 0) ? 0 : indices[i];
                char temp = characters[j];
                characters[j] = characters[i];
                characters[i] = temp;
                result.add(new String(characters));
                indices[i]++;
                i = 0;
            } else {
                indices[i] = 0;
                i++;
            }
        }
        return result;
    }
    public static List<String> recursivePermutations(String str) {
        List<String> result = new ArrayList<>();
        permute(str.toCharArray(), 0, result);
        return result;
    }

    private static void permute(char[] arr, int index, List<String> result) {
        if (index == arr.length - 1) {
            result.add(new String(arr));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            permute(arr, index + 1, result);
            swap(arr, index, i);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        List<String> iterativeResult = iterativePermutations(input);
        List<String> recursiveResult = recursivePermutations(input);

        boolean areEqual = iterativeResult.size() == recursiveResult.size() && iterativeResult.containsAll(recursiveResult);

        System.out.println("Permutations using Iterative Method: " + iterativeResult);
        System.out.println("Permutations using Recursive Method: " + recursiveResult);
        System.out.println("Are both methods' results equal? " + areEqual);
    }
}