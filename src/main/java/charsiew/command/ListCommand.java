package charsiew.command;

import charsiew.task.TaskList;
import charsiew.ui.Ui;
import charsiew.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
