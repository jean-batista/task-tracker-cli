package main;

import java.time.LocalDateTime;
import java.util.List;

import main.mapper.TaskMapper;
import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.utils.FileUtils;

public class App {
    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        TaskRepository repository = new TaskRepositoryImpl(fileUtils);

        // List<Task> tasks = repository.findAll();
        // for(Task t : tasks) {
        //     System.out.println(t);
        // }

        // Task task = repository.findById(5L).orElseThrow();
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

        // Task task = new Task(null, "Test6", TaskStatus.DONE, LocalDateTime.now(), LocalDateTime.now());
        // repository.save(task);

        // Task task = repository.findById(6L).orElseThrow();
        // repository.update(new Task(6L, "Custom task 5432", TaskStatus.DONE, LocalDateTime.now(), LocalDateTime.now()));
        // task = repository.findById(6L).orElseThrow();
        // System.out.println(task);

        // repository.delete(4L);

        // for(Task t : repository.findAll()) {
        //     System.out.println(t);
        // }

    }
}
