package charsiew.command;

import java.io.IOException;

import charsiew.storage.Storage;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.task.Todo;
import charsiew.ui.Ui;

/**
 * Represents a command to add a Todo task to the TaskList.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the given task description.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new Todo task, adds it to the TaskList, saves the list,
     * and informs the user via the UI.
     *
     * @param tasks   The TaskList to add the new Todo to.
     * @param ui      The UI object used to display messages.
     * @param storage The Storage object used to persist the TaskList.
     * @throws IOException If saving to storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        return ui.showAddedTask(t, tasks.size());
    }
}
