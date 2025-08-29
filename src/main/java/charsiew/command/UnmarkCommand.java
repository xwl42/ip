package charsiew.command;

import charsiew.task.TaskList;
import charsiew.task.Task;
import charsiew.ui.Ui;
import charsiew.storage.Storage;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.get(index - 1);
        t.markAsNotDone();
        storage.save(tasks);
        ui.showUnmark(t);
    }
}
