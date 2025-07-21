package main.repositories.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import main.exceptions.TaskException;
import main.exceptions.TaskNotFoundException;
import main.mapper.TaskMapper;
import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.repositories.TaskRepository;
import main.utils.FileUtils;

public class TaskRepositoryImpl implements TaskRepository {

    private FileUtils fileUtils;

    public TaskRepositoryImpl(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    @Override
    public Task save(Task task) {
        if(task == null) throw new TaskException("The task is null");
        fileUtils.createDirectoryAndDatabaseFile();
        task.setId(nextId());
        LocalDateTime created = LocalDateTime.now();
        task.setCreatedAt(created);
        task.setUpdatedAt(created);
        fileUtils.writeDatabaseFile(task);
        return task;
    }

    @Override
    public Task update(Task entity) {
        if(entity == null) throw new TaskException("The task is null");
        List<Task> tasks = findAll();
        Task task = findById(entity.getId()).orElseThrow(TaskNotFoundException::new);
        entity.setCreatedAt(task.getCreatedAt());
        entity.setUpdatedAt(LocalDateTime.now());
        tasks.set(tasks.indexOf(task), entity);
        fileUtils.clearDatabaseFile();
        for(Task t : tasks) {
            fileUtils.writeDatabaseFile(t);
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        List<Task> tasks = findAll();
        Task task = findById(id).orElseThrow(TaskNotFoundException::new);
        tasks.remove(task);
        fileUtils.clearDatabaseFile();
        for(Task t : tasks) {
            fileUtils.writeDatabaseFile(t);
        }
    }

    @Override
    public Optional<Task> findById(Long id) {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst();
    }

    @Override
    public List<Task> findAll() {
        fileUtils.createDirectoryAndDatabaseFile();
        String json = fileUtils.readDatabaseFile();
        if(json.isBlank()) return new ArrayList<>();
        List<Task> tasks = TaskMapper.parseJsonListToTaskList(json);
        return tasks;
    }

    @Override
    public List<Task> findAllTasksTodo() {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getStatus(), TaskStatus.TODO)).toList();
    }

    @Override
    public List<Task> findAllTasksInProgress() {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getStatus(), TaskStatus.IN_PROGRESS)).toList();
    }

    @Override
    public List<Task> findAllTasksDone() {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getStatus(), TaskStatus.DONE)).toList();
    }

    private long nextId() {
        List<Task> tasks = findAll();
        if(tasks.isEmpty()) return 1L;
        return tasks.stream().mapToLong(Task::getId).max().getAsLong() + 1;
    }
    
}
