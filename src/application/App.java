package application;

import java.time.LocalDateTime;

import model.entities.Task;
import model.enums.TaskStatus;

public class App {
    public static void main(String[] args) throws Exception {
        Task task = new Task(null, "Test", TaskStatus.TODO, LocalDateTime.now(), LocalDateTime.now());
        System.out.println(task);
    }
}
