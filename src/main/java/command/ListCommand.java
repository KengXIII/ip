package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * ListCommand will list out the task number, task description and date(if applicable) when executed.
 */
public class ListCommand extends Command {

        private final boolean EXIT = false;

        public String execute(TaskList tasks, Ui ui, Storage storage) {
            ArrayList<Task> taskList = tasks.getTaskList();
            String returnedString = "";
            for (int i = 0; i < taskList.size(); i++) {
                returnedString += String.format("\t%d.%s%n", (i + 1), taskList.get(i));
            }
            return returnedString;
        }

        public boolean isExit() {
            return EXIT;
        }
}
