package charsiew.command;

import charsiew.task.TaskList;
import charsiew.task.Task;
import charsiew.task.Deadline;
import charsiew.ui.Ui;
import charsiew.storage.Storage;
import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    public static DeadlineCommand fromInput(String input) {
        String[] parts = input.split("/by", 2);
        String desc = parts[0].trim();
        LocalDate by = LocalDate.parse(parts[1].trim());
        return new DeadlineCommand(desc, by);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Deadline(description, by);
        tasks.add(t);
        storage.save(tasks);
        ui.showAddedTask(t, tasks.size());
    }
}
