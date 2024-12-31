package DataStructurePrograms;

import java.io.*;
import java.util.*;

class LinkedListNode<T> {
    T data;
    LinkedListNode<T> next;

    public LinkedListNode(T data) {
        this.data = data;
        this.next = null;
    }
}

class OrderedLinkedList<T extends Comparable<T>> {
    private LinkedListNode<T> head;

    public OrderedLinkedList() {
        head = null;
    }

    public void insert(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        if (head == null || head.data.compareTo(data) >= 0) {
            newNode.next = head;
            head = newNode;
        } else {
            LinkedListNode<T> current = head;
            while (current.next != null && current.next.data.compareTo(data) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public boolean search(T data) {
        LinkedListNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void delete(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        LinkedListNode<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        LinkedListNode<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public void printList() {
        LinkedListNode<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class HashingFunction {
    private static final int SLOT_COUNT = 11;
    private static Map<Integer, OrderedLinkedList<Integer>> slots;

    public static void main(String[] args) {
        slots = new HashMap<>();
        for (int i = 0; i < SLOT_COUNT; i++) {
            slots.put(i, new OrderedLinkedList<>());
        }

        readNumbersFromFile("numbers.txt");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Search a number");
            System.out.println("2. Insert a number");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.print("Enter a number to search: ");
                int number = scanner.nextInt();
                int slot = number % SLOT_COUNT;
                if (slots.get(slot).search(number)) {
                    System.out.println("Number found in slot " + slot);
                    slots.get(slot).delete(number);
                    System.out.println("Number removed from the list.");
                } else {
                    System.out.println("Number not found.");
                }
            } else if (choice == 2) {
                System.out.print("Enter a number to insert: ");
                int number = scanner.nextInt();
                int slot = number % SLOT_COUNT;
                if (!slots.get(slot).search(number)) {
                    slots.get(slot).insert(number);
                    System.out.println("Number inserted.");
                } else {
                    System.out.println("Number already exists.");
                }
            } else if (choice == 3) {
                break;
            }
        }
        saveNumbersToFile("numbers.txt");

        System.out.println("\nNumbers in all slots:");
        for (int i = 0; i < SLOT_COUNT; i++) {
            System.out.print("Slot " + i + ": ");
            slots.get(i).printList();
        }

        scanner.close();
    }

    private static void readNumbersFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                int slot = number % SLOT_COUNT;
                slots.get(slot).insert(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveNumbersToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < SLOT_COUNT; i++) {
                List<Integer> numbers = slots.get(i).toList();
                for (Integer number : numbers) {
                    bw.write(number + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}