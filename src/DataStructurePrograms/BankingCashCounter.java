package DataStructurePrograms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Customer {
    String name;
    String transactionType;
    double amount;

    public Customer(String name, String transactionType, double amount) {
        this.name = name;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}

class BankingQueue<T> {
    private Queue<T> queue;

    public BankingQueue() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}

class Bank {
    private double cashBalance;

    public Bank(double initialBalance) {
        this.cashBalance = initialBalance;
    }

    public void deposit(double amount) {
        cashBalance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (cashBalance >= amount) {
            cashBalance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
            return false;
        }
    }

    public double getCashBalance() {
        return cashBalance;
    }
}

public class BankingCashCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingQueue<Customer> queue = new BankingQueue<>();
        Bank bank = new Bank(10000.0);

        while (true) {
            System.out.println("Banking Cash Counter");
            System.out.println("1. Add Customer for Deposit");
            System.out.println("2. Add Customer for Withdrawal");
            System.out.println("3. Process Next Customer");
            System.out.println("4. Check Cash Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1 || choice == 2) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine();
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                if (choice == 1) {
                    queue.enqueue(new Customer(name, "deposit", amount));
                } else if (choice == 2) {
                    queue.enqueue(new Customer(name, "withdraw", amount));
                }
                System.out.println("Customer added to the queue.");
            }
            else if (choice == 3) {
                if (!queue.isEmpty()) {
                    Customer currentCustomer = queue.dequeue();
                    System.out.println("Processing " + currentCustomer.name + "'s " + currentCustomer.transactionType + " of $" + currentCustomer.amount);

                    if (currentCustomer.transactionType.equals("deposit")) {
                        bank.deposit(currentCustomer.amount);
                    } else if (currentCustomer.transactionType.equals("withdraw")) {
                        bank.withdraw(currentCustomer.amount);
                    }
                } else {
                    System.out.println("No customers in the queue.");
                }
            }
            else if (choice == 4) {
                System.out.println("Current Cash Balance: $" + bank.getCashBalance());
            }
            else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
