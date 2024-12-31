package DataStructurePrograms;

import java.io.*;
import java.util.Scanner;
// Ordered Linked List class with generics
class OrderedList<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    public OrderedList() {
        head = null;
        size = 0;
    }

    // Add an item in ascending order
    public void add(T item) {
        Node<T> newNode = new Node<>(item);

        if (head == null || head.data.compareTo(item) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null && current.next.data.compareTo(item) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // Remove an item from the list
    public void remove(T item) {
        if (head == null) return;

        if (head.data.equals(item)) {
            head = head.next;
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(item)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }

    // Search for an item in the list
    public boolean search(T item) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the size of the list
    public int size() {
        return size;
    }

    // Get the index of an item
    public int index(T item) {
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Item not found
    }

    // Pop the last item from the list
    public T pop() {
        if (head == null) {
            return null;
        }

        Node<T> current = head;
        while (current.next != null && current.next.next != null) {
            current = current.next;
        }

        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }

    // Pop the item at a specific position
    public T pop(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (pos == 0) {
            T data = head.data;
            head = head.next;
            size--;
            return data;
        }

        Node<T> current = head;
        for (int i = 0; i < pos - 1; i++) {
            current = current.next;
        }

        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }

    // Print the list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Save the list to a file
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        Node<T> current = head;
        while (current != null) {
            writer.write(current.data.toString());
            writer.newLine();
            current = current.next;
        }
        writer.close();
    }

    // Load the list from a file
    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            add((T) Integer.valueOf(line)); // Convert string to Integer for numbers
        }
        reader.close();
    }
}

public class OrderedListExample {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Create a new ordered linked list of Integers
        OrderedList<Integer> list = new OrderedList<>();

        String filename = "numbers.txt";
        File file = new File(filename);

        // Check if the file exists, if not create it with some default numbers
        if (!file.exists()) {
            System.out.println("File not found. Creating and populating the file with default numbers...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("10\n");
            writer.write("20\n");
            writer.write("30\n");
            writer.write("40\n");
            writer.write("50\n");
            writer.close();
        }

        // Load numbers from the file
        list.loadFromFile(filename);

        // Print the list
        System.out.println("List of numbers:");
        list.printList();

        // Take user input for a number
        System.out.print("Enter a number to search: ");
        int number = scanner.nextInt();

        if (list.search(number)) {
            System.out.println("Number found! Removing it.");
            list.remove(number);
        } else {
            System.out.println("Number not found! Inserting it in the correct position.");
            list.add(number);
        }

        // Print the modified list
        System.out.println("Modified list of numbers:");
        list.printList();

        // Save the modified list back to the file using saveToFile
        list.saveToFile(filename);

        scanner.close();
    }
}
