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
                    System.out.println(" Nice! Treat yourself with more Char Siew :)");
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


            } else if (input.startsWith("todo")) {
                String desc = input.substring(4).trim();
                Task t = new Todo(desc);
                tasks[taskCount++] = t;
                printAddedTask(t, taskCount);

            } else if (input.startsWith("deadline")) {
                String[] parts = input.substring(8).split("/by", 2);
                Task t = new Deadline(parts[0].trim(), parts[1].trim());
                tasks[taskCount++] = t;
                printAddedTask(t, taskCount);

            } else if (input.startsWith("event")) {
                String[] parts = input.substring(5).split("/from|/to");
                Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks[taskCount++] = t;
                printAddedTask(t, taskCount);
            }
        }

        scanner.close();
    }

    private static void printAddedTask(Task t, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + count + " tasks in the plate.");
        System.out.println(" Remember not to burn yourself out... or your Char Siew! ");
        System.out.println("____________________________________________________________");
    }
}
