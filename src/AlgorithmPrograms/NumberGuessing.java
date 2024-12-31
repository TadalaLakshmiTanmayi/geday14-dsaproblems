package AlgorithmPrograms;
import java.util.Scanner;

public class NumberGuessing {

    public static int guessNumber(int low, int high, int n) {
        if (low >= high) return low;
        int mid = (low + high) / 2;
        Scanner sc = new Scanner(System.in);
        System.out.println("Is your number between " + low + " and " + mid + "? (yes/no)");
        String response = sc.next();
        if (response.equals("yes")) return guessNumber(low, mid, n);
        else return guessNumber(mid + 1, high, n);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number N where N = 2^n (e.g., 16, 32, 64):");
        int N = sc.nextInt();
        int result = guessNumber(0, N - 1, N);
        System.out.println("Your number is: " + result);
        sc.close();
    }
}
