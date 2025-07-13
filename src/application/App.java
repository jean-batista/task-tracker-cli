package application;

import java.util.List;

import model.entities.Task;
import repositories.TaskRepository;
import repositories.impl.TaskRepositoryImpl;

public class App {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepositoryImpl();
        List<Task> tasks = repository.findAll();
        for(Task task : tasks) {
            System.out.println(task);
        }
    }
}
