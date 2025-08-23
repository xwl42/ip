import java.util.Scanner;

public class CharSiew {
    public static void main(String[] args) {
        // Greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hey there! I'm Char Siew, the chatbot your mom wishes you were.");
        System.out.println(" What can I do for you today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" See you soon! May your life be less roasted than me.");
                System.out.println("____________________________________________________________");
                break;


            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");


            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Okie! Treat yourself with more Char Siew :)");
                    System.out.println("   " + tasks[index]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Invalid task number.");
                }


            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted! Energise yourself with more Char Siew ;)");
                    System.out.println("   " + tasks[index]);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Invalid task number.");
                }


            } else {
                // Store the task
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(input);
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + input);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Task list is full! Enough of Char Siew for the day.");
                    System.out.println("____________________________________________________________");
                }
            }
        }
        // Level-3
        scanner.close();
    }
}
