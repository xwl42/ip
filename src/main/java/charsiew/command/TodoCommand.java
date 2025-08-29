package charsiew.command;

import charsiew.task.TaskList;
import charsiew.task.Task;
import charsiew.task.Todo;
import charsiew.ui.Ui;
import charsiew.storage.Storage;
import charsiew.exception.CharSiewException;
import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        ui.showAddedTask(t, tasks.size());
    }
}
