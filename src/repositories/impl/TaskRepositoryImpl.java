package repositories.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import exceptions.FileCannotBeCreatedException;
import exceptions.FileCannotBeReadException;
import exceptions.FileCannotBeWrittenException;
import exceptions.FileIsEmptyException;
import exceptions.TaskException;
import exceptions.TaskNotFoundException;
import mapper.TaskMapper;
import model.entities.Task;
import model.enums.TaskStatus;
import repositories.TaskRepository;

public class TaskRepositoryImpl implements TaskRepository {

    private final Path DATABASE_PATH = Path.of("database");

    private final String DATABASE_NAME = "database.json";
    
    private final Path DATABASE_COMPLETE_PATH = Path.of(DATABASE_PATH + "/" + DATABASE_NAME);

    @Override
    public Task save(Task task) {
        if(task == null) throw new TaskException("The task is null");
        createDirectoryAndDatabaseFile();
        task.setId(nextId());
        writeDatabaseFile(task);
        return task;
    }

    @Override
    public Task update(Task entity) {
        if(entity == null) throw new TaskException("The task is null");
        List<Task> tasks = findAll();
        Task task = findById(entity.getId()).orElseThrow(TaskNotFoundException::new);
        tasks.set(tasks.indexOf(task), entity);
        clearDatabaseFile();
        for(Task t : tasks) {
            writeDatabaseFile(t);
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        List<Task> tasks = findAll();
        Task task = findById(id).orElseThrow(TaskNotFoundException::new);
        tasks.remove(task);
        clearDatabaseFile();
        for(Task t : tasks) {
            writeDatabaseFile(t);
        }
    }

    @Override
    public Optional<Task> findById(Long id) {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst();
    }

    @Override
    public List<Task> findAll() {
        createDirectoryAndDatabaseFile();
        List<Task> tasks = new ArrayList<>();
        String json = readDatabaseFile();
        if(json.isBlank()) return tasks;
        Task task;
        List<Map<String, String>> tasksMap = new ArrayList<>();
        Map<String, String> map;
        try(BufferedReader br = new BufferedReader(new FileReader(DATABASE_COMPLETE_PATH.toString()))) {
            json = json.replace("[", "").replace("]", "")
                .replace("},{", "/").replace("{", "")
                .replace("}", "").replace("\":\"", "=").replace("\"", "");
            String[] tasksArray = json.split("/");
            String[] attributesArray;
            String key;
            String value;
            for(int i = 0; i < tasksArray.length; i++) {
                map = new HashMap<>();
                attributesArray = tasksArray[i].split(",");
                for(int j = 0; j < attributesArray.length; j++) {
                    key = attributesArray[j].split("=")[0];
                    value = attributesArray[j].split("=")[1];
                    map.put(key, value);
                }
                tasksMap.add(map);
            }
            for(var m : tasksMap) {
                task = createTask(m);
                tasks.add(task);
            }
        } catch(IOException e) {
            throw new FileCannotBeReadException();
        }
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

    private String readDatabaseFile() {
        String string = "";
        try {
            List<String> lines = Files.readAllLines(DATABASE_COMPLETE_PATH);
            if(!lines.isEmpty()) string = lines.getFirst();
        } catch(IOException e) {
            throw new FileCannotBeReadException();
        }
        if(string == null) throw new FileIsEmptyException();
        return string;
    }

    private void createDirectoryAndDatabaseFile() {
        try {
            if(!Files.exists(DATABASE_PATH)) Files.createDirectories(DATABASE_PATH);
            if(!Files.exists(DATABASE_COMPLETE_PATH)) 
                Files.createFile(DATABASE_COMPLETE_PATH);
        } catch(IOException e) {
            throw new FileCannotBeCreatedException();
        }
    }

    private Task createTask(Map<String, String> map) {
        if(map == null) throw new TaskException("Map is null");
        return new Task(
            Long.parseLong(map.get("id")),
            map.get("description"),
            TaskStatus.valueOf(map.get("status")),
            LocalDateTime.parse(map.get("createdAt")),
            LocalDateTime.parse(map.get("updatedAt"))
        );
    }

    private long nextId() {
        List<Task> tasks = findAll();
        if(tasks.isEmpty()) return 1L;
        long id = tasks.getFirst().getId();
        for(Task task : tasks) {
            if(id < task.getId()) id = task.getId();
        }
        return ++id;
    }

    private void writeDatabaseFile(Task task) {
        String json = TaskMapper.parseTaskToJson(task);
        String string = readDatabaseFile();
        if(!string.isBlank()) string = string.replace("]", ",").concat(json).concat("]");
        if(string.isBlank()) string = string.concat("[").concat(json).concat("]");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_COMPLETE_PATH.toString()))) {
            bw.write(string);
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }

    private void clearDatabaseFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_COMPLETE_PATH.toString()))) {
            bw.write("");
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }
    
}
