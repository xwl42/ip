public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui handling interactions.
     * @param storage The Storage for saving/loading tasks.
     * @throws Exception If something goes wrong during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Returns true if this command exits the program.
     */
    public boolean isExit() {
        return false;
    }
}
