package DataStructurePrograms;

import java.util.*;

public class CalendarProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();

        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        int[][] calendar = new int[6][7];
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        int totalDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int day = 1;
        int week = 0;
        for (int i = firstDayOfMonth - 1; i < 7 && day <= totalDaysInMonth; i++) {
            calendar[week][i] = day++;
        }

        while (day <= totalDaysInMonth) {
            week++;
            for (int i = 0; i < 7 && day <= totalDaysInMonth; i++) {
                calendar[week][i] = day++;
            }
        }

        System.out.println("Sun Mon Tue Wed Thu Fri Sat");
        for (int i = 0; i < 6; i++) {
            boolean weekEmpty = true;
            for (int j = 0; j < 7; j++) {
                if (calendar[i][j] != 0) {
                    weekEmpty = false;
                    System.out.print(String.format("%2d ", calendar[i][j]));
                } else {
                    System.out.print("   ");
                }
            }
            if (!weekEmpty) {
                System.out.println();
            }
        }

        scanner.close();
    }
}
