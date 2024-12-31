package DataStructurePrograms;

import java.util.LinkedList;
import java.util.Scanner;

class Deque<T> {
    private LinkedList<T> deque;

    public Deque() {
        deque = new LinkedList<>();
    }

    public void addFront(T item) {
        deque.addFirst(item);
    }

    public void addRear(T item) {
        deque.addLast(item);
    }

    public T removeFront() {
        return deque.pollFirst();
    }

    public T removeRear() {
        return deque.pollLast();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public int size() {
        return deque.size();
    }
}

public class PalindromeChecker {
    public static boolean isPalindrome(String str) {
        Deque<Character> deque = new Deque<>();

        for (int i = 0; i < str.length(); i++) {
            deque.addRear(str.charAt(i));
        }

        while (deque.size() > 1) {
            if (deque.removeFront() != deque.removeRear()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        if (isPalindrome(inputString)) {
            System.out.println("The string is a palindrome.");
        } else {
            System.out.println("The string is not a palindrome.");
        }

        scanner.close();
    }
}

