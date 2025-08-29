package charsiew.parser;

import charsiew.command.*;

/**
 * Parses user input into corresponding Command objects.
 * Acts as the bridge between raw user input and command execution.
 */
public class Parser {

    /**
     * Parses a raw input string and returns the appropriate Command.
     *
     * @param input Raw string entered by the user.
     * @return A Command object corresponding to the input.
     * @throws Exception If the input cannot be parsed into a valid command.
     */
    public static Command parse(String input) throws Exception {
        String[] words = input.trim().split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(words[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(words[1]));
        case "todo":
            return new TodoCommand(words[1]);
        case "deadline":
            return DeadlineCommand.fromInput(words[1]);
        case "event":
            return EventCommand.fromInput(words[1]);
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "bye":
            return new ByeCommand();
        default:
            throw new Exception("I'm not too sure what that means...");
        }
    }
}
