package main.services.impl;

import java.util.List;

import main.exceptions.TaskNotFoundException;
import main.model.entities.Task;
import main.repositories.TaskRepository;
import main.services.TaskService;

public class TaskServiceImpl implements TaskService {

    private TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task save(Task entity) {
        return repository.save(entity);
    }

    @Override
    public Task update(Task entity) {
        return repository.update(entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Task findById(Long id) {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Task> findAllTasksTodo() {
        return repository.findAllTasksTodo();
    }

    @Override
    public List<Task> findAllTasksInProgress() {
        return repository.findAllTasksInProgress();
    }

    @Override
    public List<Task> findAllTasksDone() {
        return repository.findAllTasksDone();
    }
    
}
