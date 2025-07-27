package test.main.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.model.entities.Task;
import main.model.enums.TaskStatus;

public class TestUtils {

    public void deleteTestDatabase() {
        File file = new File("src/test/resources/database/databasetest.json");
        file.delete();
    }

    public List<Task> createTaskList(int quantity, String description) {
        Task task;
        List<Task> list = new ArrayList<>();
        int random;
        for(int i = 1; i <= quantity; i++) {
            random = (int) (Math.random() * 3) + 1;
            task = new Task(Long.valueOf(i), description + i, TaskStatus.fromCode(random), null, null);
            list.add(task);
        }
        return list;
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
