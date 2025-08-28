import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file/folder doesn't exist, returns an empty TaskList.
     */
    public TaskList load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            // Create parent folder if missing
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            // Return empty task list (first-time run)
            return new TaskList();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        sc.close();
        return new TaskList(tasks);
    }

    /**
     * Saves tasks into the storage file.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.getAllTasks()) {
            fw.write(formatTask(t) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parse a line of text into a Task object.
     * Example format: "T | 1 | read book"
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String name = parts[2];

            switch (type) {
                case "T":
                    Todo todo = new Todo(name);
                    if (isDone) todo.markAsDone();
                    return todo;
                case "D":
                    LocalDate by = LocalDate.parse(parts[3]); // yyyy-MM-dd
                    Deadline deadline = new Deadline(name, by);
                    if (isDone) deadline.markAsDone();
                    return deadline;
                case "E":
                    LocalDate from = LocalDate.parse(parts[3]);
                    LocalDate to = LocalDate.parse(parts[4]);
                    Event event = new Event(name, from, to);
                    if (isDone) event.markAsDone();
                    return event;
                default:
                    return null; // corrupted line
            }
        } catch (Exception e) {
            // corrupted line
            return null;
        }
    }

    /**
     * Converts a Task into savable string format.
     */
    private String formatTask(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.name;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.name  + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.name + " | " + e.from + " | " + e.to;
        }
        return "";
    }
}
