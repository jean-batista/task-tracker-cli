package test.main.utils;

import java.io.File;

import main.model.entities.Task;
import main.model.enums.TaskStatus;

public class TestUtils {

    public void deleteTestDatabase() {
        File file = new File("src/test/resources/database/databasetest.json");
        file.delete();
    }

    public Task createTaskEntity(Long id, String description, TaskStatus status) {
        return new Task(
            id,
            description,
            status,
            null,
            null
        );
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
