package main.repositories;

import java.util.List;
import java.util.Optional;

import main.model.entities.Task;

public interface TaskRepository {
    Task save(Task entity);
    Task update(Task entity);
    void delete(Long id);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    List<Task> findAllTasksTodo();
    List<Task> findAllTasksInProgress();
    List<Task> findAllTasksDone();
}
