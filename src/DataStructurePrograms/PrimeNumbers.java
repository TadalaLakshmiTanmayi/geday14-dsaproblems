package DataStructurePrograms;

public class PrimeNumbers {
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

        int[][] primeRanges = new int[10][];
        for (int i = 0; i < 10; i++) {
            primeRanges[i] = new int[100];
        }

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                int rangeIndex = i / 100;
                primeRanges[rangeIndex] = addElement(primeRanges[rangeIndex], i);
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.print("Range " + (i * 100) + " - " + ((i + 1) * 100 - 1) + ": ");
            for (int num : primeRanges[i]) {
                if (num != 0) {
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }
    }
    public static int[] addElement(int[] arr, int num) {
        int size = 0;
        while (size < arr.length && arr[size] != 0) {
            size++;
        }
        if (size < arr.length) {
            arr[size] = num;
        }
        return arr;
    }
}
