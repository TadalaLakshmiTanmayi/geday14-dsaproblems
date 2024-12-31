package DataStructurePrograms;
import java.util.*;

class StackUsingLinkedList<T> {
    private LinkedListNode<T> top;

    public StackUsingLinkedList() {
        top = null;
    }

    public void push(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            return null;
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class PrimeNumbersAnagramStack {

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

        StackUsingLinkedList<Integer> anagramPrimesStack = new StackUsingLinkedList<>();
        Map<String, List<Integer>> anagramGroups = new HashMap<>();

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
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
                        anagramPrimesStack.push(prime);
                    }
                }
            }
        }

        System.out.println("Anagram Prime Numbers in Reverse Order:");
        while (!anagramPrimesStack.isEmpty()) {
            System.out.print(anagramPrimesStack.pop() + " ");
        }
    }

    private static String sortDigits(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }
}
