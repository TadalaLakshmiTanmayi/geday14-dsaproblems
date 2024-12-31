package DataStructurePrograms;
import java.util.*;


class QueueUsingLinkedList<T> {
    private LinkedListNode<T> front;
    private LinkedListNode<T> rear;

    public QueueUsingLinkedList() {
        front = null;
        rear = null;
    }

    public void enqueue(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (front == null) {
            return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

public class PrimeNumbersAnagramQueue {

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

        QueueUsingLinkedList<Integer> anagramPrimesQueue = new QueueUsingLinkedList<>();
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
                        anagramPrimesQueue.enqueue(prime);
                    }
                }
            }
        }

        System.out.println("Anagram Prime Numbers from Queue:");
        while (!anagramPrimesQueue.isEmpty()) {
            System.out.print(anagramPrimesQueue.dequeue() + " ");
        }
    }

    private static String sortDigits(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }
}

