import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CharSiew {
    private static final String FILE_PATH = "./data/char_siew_tasks.txt";

    public static void main(String[] args) throws CharSiewException {
        // Greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hey there! I'm Char Siew, the chatbot your mom wishes you were.");
        System.out.println(" What can I do for you today?");
        System.out.println("   Command                                 Effect ");
        System.out.println(" - list                                    show all tasks");
        System.out.println(" - todo task_name                          add a new todo task");
        System.out.println(" - deadline task_name /by yyyy-mm-dd       add a new deadline");
        System.out.println(" - event /from yyyy-mm-dd /to yyyy-mm-dd   add a new event");
        System.out.println(" - mark task_index                         mark the task done");
        System.out.println(" - unmark task_index                       mark the task undone");
        System.out.println(" - delete task_index                       delete the task");
        System.out.println(" - bye                                     exit");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();


        // Load tasks from file if it exists
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String name = parts[2];

                    Task t = null;
                    switch (type) {
                    case "T":
                        t = new Todo(name);
                        break;
                    case "D":
                        t = new Deadline(name, parts[3]);
                        break;
                    case "E":
                        t = new Event(name, parts[3], parts[4]);
                        break;
                    }

                    if (t != null && isDone) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                }
            } catch (IOException e) {
                System.out.println("Failed to load tasks: " + e.getMessage());
            }
        }


        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" See you soon! May your life be less roasted than me.");
                    System.out.println("____________________________________________________________");
                    break;


                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");


                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        saveTasks(tasks);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! Treat yourself with more Char Siew :)");
                        System.out.println("   " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new CharSiewException("Task number is out of range. Mind your boundaries, even with Char Siew!");
                    }

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        saveTasks(tasks);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted! Energise yourself with more Char Siew ;)");
                        System.out.println("   " + tasks.get(index));
                        System.out.println("____________________________________________________________");
                    } else {
                        throw new CharSiewException("Task number is out of range. Mind your boundaries, even with Char Siew!");
                    }


                } else if (input.startsWith("todo")) {
                    String desc = input.substring(4).trim();
                    if (desc.isEmpty()) {
                        throw new CharSiewException("Enlighten me... What's the todo exactly? O_o");
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    printAddedTask(t, tasks.size());
                    saveTasks(tasks);

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new CharSiewException("Enlighten me... What's the thing and when is it due exactly? O_o");
                    }

                    try {
                        Task t = new Deadline(parts[0].trim(), parts[1].trim());
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                        saveTasks(tasks);
                    } catch (DateTimeParseException e) {
                        throw new CharSiewException("Hmmm... The date must be in yyyy-mm-dd format.");
                    }


                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new CharSiewException("Enlighten me... What's the event and when does it happen exactly? O_o");
                    }

                    try {
                        Task t = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                        tasks.add(t);
                        printAddedTask(t, tasks.size());
                        saveTasks(tasks);
                    } catch (DateTimeParseException e) {
                        throw new CharSiewException("Hmmm... The date must be in yyyy-mm-dd format.");
                    }


                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new CharSiewException("Index out of range. Mind your boundaries, even with Char Siew!");
                    }
                    Task removed = tasks.remove(index);
                    saveTasks(tasks);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the plate.");
                    System.out.println("____________________________________________________________");


                } else {
                    throw new CharSiewException("I'm not too sure what you mean... I'm just a simple-minded piece of Char Siew o_O ");
                }


            } catch (CharSiewException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Task number should literally be a positive integer... Show me (or your mom) you're better than a piece of Char Siew!");
                System.out.println("____________________________________________________________");
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

    private static void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // ensure folder exists
        }

        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks) {
                String line = "";
                if (t instanceof Todo) {
                    line = "T | " + (t.isDone() ? "1" : "0") + " | " + t.getName();
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    line = "D | " + (d.isDone() ? "1" : "0") + " | " + d.getName() + " | " + d.getBy();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = "E | " + (e.isDone() ? "1" : "0") + " | " + e.getName() + " | " + e.getFrom() + " | " + e.getTo();
                }
                fw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

}
