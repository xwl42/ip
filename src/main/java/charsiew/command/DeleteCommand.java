package charsiew.command;

import java.io.IOException;

import charsiew.storage.Storage;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index the 1-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the given index
     * from the TaskList, saving the updated list, and showing a confirmation message.
     *
     * @param tasks   The TaskList from which a task will be removed.
     * @param ui      The UI object to show messages.
     * @param storage The Storage object to save changes.
     * @throws IOException if saving the updated TaskList fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.remove(index - 1);
        storage.save(tasks);
        return ui.showDelete(t, tasks.size());
    }
}
