package charsiew.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Finds all tasks whose description contains the given keyword.
     * The search is case-insensitive.
     *
     * @param keyword the keyword to search for
     * @return a {@code TaskList} containing all matching tasks
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matching = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matching.add(t);
            }
        }
        return new TaskList(matching);
    }


    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
