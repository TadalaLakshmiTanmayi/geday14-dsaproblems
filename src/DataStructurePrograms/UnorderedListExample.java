package DataStructurePrograms;

import java.io.*;
import java.util.Scanner;

// Node class for the linked list
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Unordered Linked List class with generics
class UnorderedList<T> {
    private Node<T> head;
    private int size;

    public UnorderedList() {
        head = null;
        size = 0;
    }

    // Add an item to the list
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
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

    // Append an item to the end of the list
    public void append(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Get the index of an item in the list
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

    // Insert an item at a specific position
    public void insert(int pos, T item) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newNode = new Node<>(item);
        if (pos == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < pos - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
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
            add((T) line);
        }
        reader.close();
    }
}

public class UnorderedListExample {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Create a new unordered linked list of Strings
        UnorderedList<String> list = new UnorderedList<>();

        String filename = "words.txt";
        File file = new File(filename);

        // Check if the file exists, if not create it with some default words
        if (!file.exists()) {
            System.out.println("File not found. Creating and populating the file with default words...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("apple\n");
            writer.write("banana\n");
            writer.write("orange\n");
            writer.write("mango\n");
            writer.write("pear\n");
            writer.close();
        }

        // Load words from the file
        list.loadFromFile(filename);

        // Print the list
        System.out.println("List of words:");
        list.printList();

        // Take user input to search a word
        System.out.print("Enter a word to search: ");
        String word = scanner.nextLine();

        if (list.search(word)) {
            System.out.println("Word found! Removing it.");
            list.remove(word);
        } else {
            System.out.println("Word not found! Adding it.");
            list.add(word);
        }

        // Print the modified list
        System.out.println("Modified list of words:");
        list.printList();

        // Save the modified list back to the file
        list.saveToFile(filename);

        scanner.close();
    }
}
