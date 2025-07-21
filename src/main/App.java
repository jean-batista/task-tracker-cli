package main;

import java.util.List;

import main.config.AppConfig;
import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.services.TaskService;

public class App {
    public static void main(String[] args) {
  
        AppConfig config = new AppConfig();
        TaskService service = config.createTaskService(); 

        // List<Task> tasks = service.findAll();
        // for(Task t : tasks) {
        //     System.out.println(t);
        // }

        // Task task = service.findById(1L);
        // System.out.println(task);

        // List<Task> tasksTodo = service.findAllTasksTodo();

        // for(Task t : tasksTodo) {
        //     System.out.println(t);
        // }

        // List<Task> tasksInProgress = service.findAllTasksInProgress();
        // for(Task t : tasksInProgress) {
        //     System.out.println(t);
        // }

        // List<Task> tasksDone = service.findAllTasksDone();
        // for(Task t : tasksDone) {
        //     System.out.println(t);
        // }

        // Task task = new Task(null, "Test6", TaskStatus.DONE, null, null);
        // Task newTask = service.save(task);
        // System.out.println(newTask);

        // Task task = service.findById(1L);
        // service.update(new Task(1L, "Custom task", TaskStatus.IN_PROGRESS, null, null));
        // Task updatedTask = service.findById(1L);
        // System.out.println(task);
        // System.out.println(updatedTask);

        service.delete(2L);
        for(Task task : service.findAll()) {
            System.out.println(task);
        }

    }
}
