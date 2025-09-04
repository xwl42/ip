package charsiew.command;

import java.io.IOException;
import java.time.LocalDate;
import charsiew.storage.Storage;
import charsiew.task.Deadline;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to add a Deadline task.
 * Handles creation, addition to the task list, saving, and UI display.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a DeadlineCommand with a description and due date.
     *
     * @param description The task description.
     * @param by The due date of the task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Parses user input to create a DeadlineCommand.
     * Expected format: "description /by yyyy-MM-dd"
     *
     * @param input Raw input string after the "deadline" keyword.
     * @return A DeadlineCommand object.
     */
    public static DeadlineCommand fromInput(String input) {
        String[] parts = input.split("/by", 2);
        String desc = parts[0].trim();
        LocalDate by = LocalDate.parse(parts[1].trim());
        return new DeadlineCommand(desc, by);
    }

    /**
     * Executes the command: adds the Deadline task to the TaskList,
     * saves the updated list, and displays the task addition via UI.
     *
     * @param tasks TaskList to add the task to.
     * @param ui UI object for displaying messages.
     * @param storage Storage object for saving the task list.
     * @throws IOException If saving to storage fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Deadline(description, by);
        tasks.add(t);
        storage.save(tasks);
        ui.showAddedTask(t, tasks.size());
    }
}
