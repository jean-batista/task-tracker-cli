package application;

import java.time.LocalDateTime;
import java.util.List;

import model.entities.Task;
import model.enums.TaskStatus;
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

        // List<Task> tasksDone = repository.findAllTasksDone();

        // for(Task t : tasksDone) {
        //     System.out.println(t);
        // }

        // Task task = new Task(null, "Test7", TaskStatus.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now());
        // repository.save(task);

        // Task task = repository.findById(1L).orElseThrow();
        // repository.update(new Task(1L, "Custom task 5432", TaskStatus.DONE, LocalDateTime.now(), LocalDateTime.now()));

        repository.delete(6L);

        for(Task t : repository.findAll()) {
            System.out.println(t);
        }

    }
}
