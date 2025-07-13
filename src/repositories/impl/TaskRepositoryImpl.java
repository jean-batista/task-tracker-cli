package repositories.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import exceptions.TaskNotFoundException;
import model.entities.Task;
import model.enums.TaskStatus;
import repositories.TaskRepository;

public class TaskRepositoryImpl implements TaskRepository {

    private final Path DATABASE_PATH = Path.of("database");

    private final String DATABASE_NAME = "database.json";
    
    private final Path DATABASE_COMPLETE_PATH = Path.of(DATABASE_PATH + "/" + DATABASE_NAME);

    @Override
    public Task save(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Task update(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Task findById(Long id) {
        List<Task> tasks = findAll();
        return tasks.stream().filter(e -> Objects.equals(e.getId(), id)).findFirst().orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public List<Task> findAll() {
        createDirectoryAndDatabaseFile();
        List<Task> tasks = new ArrayList<>();
        Task task;
        List<Map<String, String>> tasksMap = new ArrayList<>();
        Map<String, String> map;
        try(BufferedReader br = new BufferedReader(new FileReader(DATABASE_COMPLETE_PATH.toString()))) {
            String string = Files.readAllLines(DATABASE_COMPLETE_PATH).getFirst();
            string = string.replace("[", "").replace("]", "")
                .replace("},{", "/").replace("{", "")
                .replace("}", "").replace("\":\"", "=").replace("\"", "");
            String[] tasksArray = string.split("/");
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
            System.out.println("The file could not be read");
            System.out.println(e.getMessage());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllTasksInProgress'");
    }

    @Override
    public List<Task> findAllTasksDone() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllTasksDone'");
    }

    private void createDirectoryAndDatabaseFile() {
        try {
            if(!Files.exists(DATABASE_PATH)) Files.createDirectories(DATABASE_PATH);
            if(!Files.exists(DATABASE_COMPLETE_PATH)) 
                Files.createFile(DATABASE_COMPLETE_PATH);
        } catch(IOException e) {
            System.out.println("The data file could not be created");
            System.out.println(e.getMessage());
        }
    }

    private Task createTask(Map<String, String> map) {
        return new Task(
            Long.parseLong(map.get("id")),
            map.get("description"),
            TaskStatus.valueOf(map.get("status")),
            LocalDateTime.parse(map.get("createdAt")),
            LocalDateTime.parse(map.get("updatedAt"))
        );
    }
    
}
