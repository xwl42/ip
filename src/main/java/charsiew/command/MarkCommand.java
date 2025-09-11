package charsiew.command;

import java.io.IOException;

import charsiew.storage.Storage;
import charsiew.task.Task;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents a command to mark a task as done in the TaskList.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand for a specific task index.
     *
     * @param index The 1-based index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specified task as done, saves the updated TaskList,
     * and informs the user via the UI.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The UI object used to display messages.
     * @param storage The Storage object used to persist the TaskList.
     * @throws IOException If saving to storage fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.get(index - 1); // 1-based indexing
        t.markAsDone();
        storage.save(tasks);
        return ui.showMark(t);
    }
}
