package test.main.config;

import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.utils.FileUtils;
import test.main.repositories.TaskRepositoryTest;
import test.main.runners.TaskRepositoryTestRunner;
import test.main.utils.TestUtils;

public class AppTestConfig {

    private final String DATABASE_PATH = "src/test/resources/database";
    private final String DATABASE_NAME = "databasetest.json";

    public TaskRepositoryTestRunner createTaskRepositoryTestRunner() {
        FileUtils fileUtils = new FileUtils(DATABASE_PATH, DATABASE_NAME);
        TestUtils testUtils = new TestUtils();
        TaskRepository repository = new TaskRepositoryImpl(fileUtils);
        TaskRepositoryTest test = new TaskRepositoryTest(repository, testUtils);
        TaskRepositoryTestRunner runner = new TaskRepositoryTestRunner(test);
        return runner;
    }

}
