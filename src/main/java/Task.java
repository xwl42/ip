public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // X if done, space if not
    }

    public boolean isDone() {
        return isDone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
