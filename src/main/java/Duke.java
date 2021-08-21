import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project Duke is a educational software project.
 * It is designed to take you through the steps of building a small software incrementally.
 */

public class Duke {
    /**
     * This is the scanner object used to get user input.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * This is the declared string that triggers the exit.
     */
    private static String cancelWord = "bye";

    /**
     * Stores the array of todos
     */
    private static ArrayList<Task> todos = new ArrayList<>();

    /**
     * Stores the current index that is awaiting to be filled.
     */
    private static int index = 0;

    /**
     * Prints out the initial greeting message.
     */
    private static void greet() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke. \n\tWhat can I do for you?\n" +
                "\t____________________________________________________________");
    }

    /**
     * Throws the corresposing DukeException when the Task input is empty.
     *
     * @param input the array of String input to check
     * @param type  the type of Task it is called with
     * @throws DukeException throws the NullDescription
     */
    private static void checkInput(String[] input, String type) throws DukeException {
        if (input.length == 1) {
            throw new NullDescription(type);
        }
    }

    /**
     * Prints out the feedback when users enter their response.
     *
     * @param response the array of String input to check
     * @throws DukeException throws the NullDescription
     */
    private static void handleInput(String response) throws DukeException {
        String[] output = response.split(" ");


        if (output.length == 0 || output[0].isEmpty() || output[0].equals(" ")) {
            throw new InvalidCommand();
        }

        String command = output[0];
        Task newTask = null;

        switch (command) {
            case "done":
                Task editedTask = todos.get(Integer.parseInt(output[1]) - 1);
                editedTask.markIsDone();
                System.out.println(String.format("\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done:\n" +
                        "\t%s\n" +
                        "\t____________________________________________________________", editedTask.toString()));
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(output[1]) - 1;
                    Task removedTask = todos.remove(index);
                    System.out.println(String.format("\tNoted. I've removed this task:\n" +
                            "\t%s\n" +
                            "\tNow you have %d tasks in the list.", removedTask.toString(), todos.size()));
                } catch (IndexOutOfBoundsException | NumberFormatException f) {
                    throw new InvalidValue();
                }
                break;
            case "todo":
                checkInput(output, "todo");
                String todoDescription = response.substring(5);
                newTask = new Todo(todoDescription);
                handleAdd(newTask);
                break;
            case "deadline":
                checkInput(output, "deadline");
                String deadlineDescription = response.substring(9);
                newTask = new Deadline(deadlineDescription);
                handleAdd(newTask);
                break;
            case "event":
                checkInput(output, "event");
                String eventDescription = response.substring(6);
                newTask = new Event(eventDescription);
                handleAdd(newTask);
                break;
            default:
                throw new InvalidCommand();
        }
    }

    private static void handleAdd(Task newTask) {
        todos.add(newTask);

        System.out.println("\t____________________________________________________________\n\t" +
                String.format("Got it. I've added this task:\n" +
                        "\t  %s\n" +
                        "\tNow you have %d tasks in the list.", newTask.toString(), todos.size()) +
                "\n\t____________________________________________________________");
    }

    /**
     * Starts the Duke chatbot.
     * Users can input String to interact with the chatbot.
     * Entering 'bye' exits the program.
     * Entering 'done' followed by an int will mark the task at that index as complete
     * Entering any other string will create a new todo.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // Initial greeting of user
        greet();

        // Starts to ask for string of instruction
        // boolean flag to indicate if loop should be exited
        boolean exit = false;

        // if boolean is false, loop will be ran
        while (!exit) {
            String response = sc.nextLine();

            switch (response) {
                case "list":
                    System.out.println("\t____________________________________________________________");
                    for (int i = 0; i < todos.size(); i++) {
                        System.out.println(String.format("\t%d.%s", (i + 1), todos.get(i).toString()));
                    }
                    System.out.println("\t____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("\t____________________________________________________________\n\t" +
                            "Bye. Hope to see you again soon!" +
                            "\n\t____________________________________________________________");
                    exit = true;
                    break;
                default:
                    try {
                        handleInput(response);
                    } catch (DukeException e) {
                        System.out.println(String.format("\t____________________________________________________________\n" +
                                "\t%s\n" +
                                "\t____________________________________________________________", e.toString()));
                    }
                    break;
            }
        }
    }
}
