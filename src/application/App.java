package application;

import java.util.List;

import model.entities.Task;
import repositories.TaskRepository;
import repositories.impl.TaskRepositoryImpl;

public class App {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepositoryImpl();

        List<Task> tasks = repository.findAll();

        for(Task t : tasks) {
            System.out.println(t);
        }

        // Task task = repository.findById(2L);
        // System.out.println(task);

        // List<Task> tasksTodo = repository.findAllTasksTodo();

        // for(Task t : tasksTodo) {
        //     System.out.println(t);
        // }

        // List<Task> tasksInProgress = repository.findAllTasksInProgress();

        // for(Task t : tasksInProgress) {
        //     System.out.println(t);
        // }

        List<Task> tasksDone = repository.findAllTasksDone();

        for(Task t : tasksDone) {
            System.out.println(t);
        }
    }
}
