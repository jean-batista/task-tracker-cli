package main.controller;

import java.util.List;

import main.model.entities.Task;
import main.services.TaskService;

public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    public Task createTask(Task entity) {
        return service.save(entity);
    }

    public Task updateTask(Task entity) {
        return service.update(entity);
    }

    public void deleteTask(Long id) {
        service.delete(id);
    }

    public Task findTaskById(Long id) {
        return service.findById(id);
    }

    public List<Task> findAllTasks() {
        return service.findAll();
    }

    public List<Task> findAllTasksTodo() {
        return service.findAllTasksTodo();
    }

    public List<Task> findAllTasksInProgress() {
        return service.findAllTasksInProgress();
    }

    public List<Task> findAllTasksDone() {
        return service.findAllTasksDone();
    }

}
