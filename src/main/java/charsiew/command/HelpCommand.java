package charsiew.command;

import charsiew.storage.Storage;
import charsiew.task.TaskList;
import charsiew.ui.Ui;

/**
 * Represents the command to get help information.
 * When executed, it shows a list of all commands available for user.
 */
public class HelpCommand extends Command {

    /**
     * Executes the Helo command by displaying list of commands available.
     *
     * @param tasks   The TaskList (unused in this command).
     * @param ui      The UI object to show messages.
     * @param storage The Storage object (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
