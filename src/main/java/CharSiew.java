import java.util.Scanner;

public class CharSiew {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public CharSiew(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (Exception e) {
            ui.showError("Failed to load Char Siew from file");
            loadedTasks = new TaskList();
        }
        tasks = loadedTasks;
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CharSiew("data/tasks.txt").run();
    }
}
