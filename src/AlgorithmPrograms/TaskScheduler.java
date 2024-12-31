package AlgorithmPrograms;

import java.util.*;

class Task {
    int deadline;
    int duration;

    Task(int deadline, int duration) {
        this.deadline = deadline;
        this.duration = duration;
    }
}

public class TaskScheduler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of tasks:");
        int T = sc.nextInt();
        Task[] tasks = new Task[T];
        for (int i = 0; i < T; i++) {
            System.out.println("deadline:"+(i+1));
            int deadline = sc.nextInt();
            System.out.println("duration:"+(i+1));
            int duration = sc.nextInt();
            tasks[i] = new Task(deadline, duration);
        }
        Arrays.sort(tasks, Comparator.comparingInt(task -> task.deadline));
        int currentTime = 0;
        int maxLateness = 0;
        for (int i = 0; i < T; i++) {
            currentTime += tasks[i].duration;
            int lateness = currentTime - tasks[i].deadline;
            if (lateness > maxLateness) {
                maxLateness = lateness;
            }
            System.out.println("maximum lateness for task"+ (i+1)+ ":" + maxLateness);
        }
        sc.close();
    }
}
