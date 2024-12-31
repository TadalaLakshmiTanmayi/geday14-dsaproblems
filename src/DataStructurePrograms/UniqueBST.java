package DataStructurePrograms;
import java.util.Scanner;

public class UniqueBST {
    private static final int MOD = 100000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[] testCases = new int[T];
        int maxN = 0;
        for (int i = 0; i < T; i++) {
            testCases[i] = scanner.nextInt();
            if (testCases[i] > maxN) {
                maxN = testCases[i];
            }
        }
        long[] catalan = new long[maxN + 1];
        catalan[0] = 1;
        catalan[1] = 1;

        for (int n = 2; n <= maxN; n++) {
            for (int i = 0; i < n; i++) {
                catalan[n] = (catalan[n] + (catalan[i] * catalan[n - 1 - i]) % MOD) % MOD;
            }
        }
        for (int n : testCases) {
            System.out.println(catalan[n]);
        }

        scanner.close();
    }
}
