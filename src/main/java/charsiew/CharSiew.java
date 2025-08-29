package charsiew;

import charsiew.task.TaskList;
import charsiew.command.Command;
import charsiew.ui.Ui;
import charsiew.parser.Parser;
import charsiew.storage.Storage;

import java.util.Scanner;

/**
 * The main CharSiew chatbot application class.
 * Handles initialization, user interaction, and the main program loop.
 */
public class CharSiew {
    /** Handles file storage and retrieval of tasks. */
    private final Storage storage;

    /** Stores the current list of tasks. */
    private final TaskList tasks;

    /** Handles all interactions with the user. */
    private final Ui ui;

    /**
     * Constructs a CharSiew instance with a given storage file path.
     * Loads tasks from storage or initializes an empty TaskList if loading fails.
     *
     * @param filePath Path to the file storing tasks.
     */
    public CharSiew(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load Char Siew from file");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    /**
     * Starts the CharSiew program and runs the main input loop.
     * Reads user commands from the console, parses them, executes them,
     * and handles any exceptions that occur.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the CharSiew application.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new CharSiew("data/tasks.txt").run();
    }
}
