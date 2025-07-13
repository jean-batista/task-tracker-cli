package repositories;

import java.util.List;

import model.entities.Task;

public interface TaskRepository {
    Task save(Task task);
    Task update(Task task);
    void delete(Long id);
    Task findById();
    List<Task> findAll();
    List<Task> findAllTasksTodo();
    List<Task> findAllTasksInProgress();
    List<Task> findAllTasksDone();
}
