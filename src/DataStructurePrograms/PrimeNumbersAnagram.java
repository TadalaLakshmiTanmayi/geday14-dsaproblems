package DataStructurePrograms;

import java.util.*;

public class PrimeNumbersAnagram {

    public static void main(String[] args) {
        int max = 1000;

        boolean[] isPrime = new boolean[max + 1];
        for (int i = 0; i <= max; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<List<Integer>> anagramPrimes = new ArrayList<>();
        List<List<Integer>> nonAnagramPrimes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            anagramPrimes.add(new ArrayList<>());
            nonAnagramPrimes.add(new ArrayList<>());
        }

        Map<String, List<Integer>> anagramGroups = new HashMap<>();

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                int rangeIndex = i / 100;
                String sortedPrime = sortDigits(i);
                if (anagramGroups.containsKey(sortedPrime)) {
                    anagramGroups.get(sortedPrime).add(i);
                } else {
                    List<Integer> primesList = new ArrayList<>();
                    primesList.add(i);
                    anagramGroups.put(sortedPrime, primesList);
                }

                if (anagramGroups.get(sortedPrime).size() > 1) {
                    for (int prime : anagramGroups.get(sortedPrime)) {
                        anagramPrimes.get(rangeIndex).add(prime);
                    }
                } else {
                    nonAnagramPrimes.get(rangeIndex).add(i);
                }
            }
        }

        System.out.println("Anagram Prime Numbers in each range:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Range " + (i * 100) + " - " + ((i + 1) * 100 - 1) + ": ");
            for (int prime : anagramPrimes.get(i)) {
                System.out.print(prime + " ");
            }
            System.out.println();
        }

        System.out.println("\nNon-Anagram Prime Numbers in each range:");
        for (int i = 0; i < 10; i++) {
            System.out.print("Range " + (i * 100) + " - " + ((i + 1) * 100 - 1) + ": ");
            for (int prime : nonAnagramPrimes.get(i)) {
                System.out.print(prime + " ");
            }
            System.out.println();
        }
    }

    private static String sortDigits(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }
}

