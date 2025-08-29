package charsiew.command;

import charsiew.task.TaskList;
import charsiew.task.Task;
import charsiew.ui.Ui;
import charsiew.storage.Storage;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.remove(index - 1);
        storage.save(tasks);
        ui.showDelete(t, tasks.size());
    }
}
