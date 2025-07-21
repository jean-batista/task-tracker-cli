package main.services;

import java.util.List;

import main.model.entities.Task;

public interface TaskService {
    
    Task save(Task entity);
    Task update(Task entity);
    void delete(Long id);
    Task findById(Long id);
    List<Task> findAll();
    List<Task> findAllTasksTodo();
    List<Task> findAllTasksInProgress();
    List<Task> findAllTasksDone();

}
