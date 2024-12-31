package AlgorithmPrograms;

import java.util.Arrays;
public class PrimeAnagramPalindrome {
    public static boolean isPalindrome(int num) {
        int original = num, reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return original == reversed;
    }

    public static boolean isPrime(int num){
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static boolean areAnagrams(int num1, int num2){
        char[] arr1 = Integer.toString(num1).toCharArray();
        char[] arr2 = Integer.toString(num2).toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 1000; i++) {
            if (isPrime(i) && isPalindrome(i)) {
                for (int j = i + 1; j <= 1000; j++) {
                    if (isPrime(j) && isPalindrome(j) && areAnagrams(i, j)) {
                        System.out.println(i + " and " + j + " are prime, palindrome, and anagrams.");
                    }
                }
            }
        }
    }
}