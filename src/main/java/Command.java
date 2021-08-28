public abstract class Command {
    public static Command parse(String input) throws InvalidCommand {
        String[] output = input.split(" ");
        if (output.length == 0 || output[0].isEmpty() || output[0].equals(" ")) {
            throw new InvalidCommand();
        }

        String command = output[0];
        Command commandType;

        switch (command) {
            case "bye":
                commandType = new ExitCommand();
            case "done":
                commandType = new DoneCommand(Integer.parseInt(output[1]) - 1);
                break;
            case "delete":
                commandType = new DeleteCommand(Integer.parseInt(output[1]) - 1);
                break;
            case "todo":

            case "deadline":

            case "event":
                commandType = new AddCommand(input);
            default:
                throw new InvalidCommand();
        }
        return commandType;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    abstract boolean isExit();
}