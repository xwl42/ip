package charsiew.ui;

import charsiew.task.Task;
import charsiew.task.TaskList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for reading user input and displaying messages.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message and lists available commands.
     */
    public void showWelcome() {
        showLine();
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
        showLine();
    }

    /** Displays a goodbye message. */
    public void showBye() {
        showLine();
        System.out.println(" See you soon! May your life be less roasted than me. ");
        showLine();
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user input as a String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Prints a horizontal line separator for UI clarity. */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added.
     *
     * @param t the task that was added
     * @param count the total number of tasks after addition
     */
    public void showAddedTask(Task t, int count) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + count + " tasks in the plate.");
        showLine();
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task the task that was marked
     */
    public void showMark(Task task) {
        showLine();
        System.out.println(" Nice! Treat yourself with more Char Siew :)");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task the task that was unmarked
     */
    public void showUnmark(Task task) {
        showLine();
        System.out.println(" Noted! Energise yourself with more Char Siew ;)");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task the task that was deleted
     * @param taskLeft the number of remaining tasks
     */
    public void showDelete(Task task, int taskLeft) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskLeft + " tasks in the plate.");
        showLine();
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    /**
     * Displays all tasks in the TaskList.
     *
     * @param tasks the TaskList containing the tasks
     */
    public void showTaskList(TaskList tasks) {
        showLine();
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty!");
            showLine();
            return;
        }

        System.out.println("Here are the tasks in your plate:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        showLine();
    }
}
