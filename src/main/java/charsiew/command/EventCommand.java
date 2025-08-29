package charsiew.command;

import charsiew.task.TaskList;
import charsiew.task.Task;
import charsiew.task.Event;
import charsiew.ui.Ui;
import charsiew.storage.Storage;
import java.io.IOException;
import java.time.LocalDate;

public class EventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public static EventCommand fromInput(String input) {
        String[] parts = input.split("/from", 2);
        String desc = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        LocalDate from = LocalDate.parse(timeParts[0].trim());
        LocalDate to = LocalDate.parse(timeParts[1].trim());

        return new EventCommand(desc, from, to);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = new Event(description, from, to);
        tasks.add(t);
        storage.save(tasks);
        ui.showAddedTask(t, tasks.size());
    }
}
