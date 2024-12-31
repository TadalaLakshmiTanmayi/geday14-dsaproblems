package DataStructurePrograms;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Calendar;
import java.util.Scanner;

class WeekDay {
    String day;
    String date;

    public WeekDay(String day, String date) {
        this.day = day;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s %s", day, date);
    }
}

class Week {
    Queue<WeekDay> weekDays;

    public Week() {
        weekDays = new LinkedList<>();
    }

    public void addWeekDay(WeekDay weekDay) {
        weekDays.add(weekDay);
    }

    public void printWeek() {
        for (WeekDay wd : weekDays) {
            System.out.print(wd + "  ");
        }
        System.out.println();
    }
}

public class WeekCalendarProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);  // Set the calendar to the first day of the given month and year
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);  // This returns the day of the week (1 = Sunday, 7 = Saturday)
        int totalDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  // Get the total number of days in the month

        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        Queue<Week> calendar = new LinkedList<>();
        Week currentWeek = new Week();
        int day = 1;

        // Adjust to fill the previous days if the first day is not Sunday
        for (int i = 1; i < firstDayOfMonth; i++) {
            currentWeek.addWeekDay(new WeekDay(weekDays[i - 1], ""));
        }

        // Loop through the days of the month and add them to the current week
        while (day <= totalDaysInMonth) {
            for (int i = 0; i < 7 && day <= totalDaysInMonth; i++) {
                currentWeek.addWeekDay(new WeekDay(weekDays[i], String.valueOf(day++)));
            }
            calendar.add(currentWeek);
            currentWeek = new Week();
        }

        System.out.println("Calendar for " + month + "/" + year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        // Print all the weeks in the calendar
        while (!calendar.isEmpty()) {
            calendar.poll().printWeek();
        }

        scanner.close();
    }
}
