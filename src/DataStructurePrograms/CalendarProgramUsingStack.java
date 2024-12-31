package DataStructurePrograms;

import java.util.Scanner;

class stackk<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;

    public stackk() {
        top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek() {
        return isEmpty() ? null : top.data;
    }
}

class weekdayy {
    String day;
    String date;

    public weekdayy(String day, String date) {
        this.day = day;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s %s", day, date);
    }
}

class weekk {
    stackk<weekdayy> weekDays;

    public weekk() {
        weekDays = new stackk<>();
    }

    public void addWeekDay(weekdayy weekDay) {
        weekDays.push(weekDay);
    }

    public void printWeek() {
        while (!weekDays.isEmpty()) {
            System.out.print(weekDays.pop() + "  ");
        }
        System.out.println();
    }
}

public class CalendarProgramUsingStack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int firstDayOfMonth = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int totalDaysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        stackk<weekk> stack1 = new stackk<>();
        stackk<weekk> stack2 = new stackk<>();
        weekk currentWeek = new weekk();
        int day = 1;

        for (int i = 1; i < firstDayOfMonth; i++) {
            currentWeek.addWeekDay(new weekdayy(weekDays[i - 1], ""));
        }

        while (day <= totalDaysInMonth) {
            for (int i = 0; i < 7 && day <= totalDaysInMonth; i++) {
                currentWeek.addWeekDay(new weekdayy(weekDays[i], String.valueOf(day++)));
            }
            stack1.push(currentWeek);
            currentWeek = new weekk();
        }

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        System.out.println("Calendar for " + month + "/" + year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        while (!stack2.isEmpty()) {
            stack2.pop().printWeek();
        }

        scanner.close();
    }
}
