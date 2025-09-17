package test.main.config;

import main.database.DatabaseManager;
import main.database.DatabaseProperties;
import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.services.ConfigurationService;
import main.services.impl.ConfigurationServiceImpl;
import test.main.repositories.TaskRepositoryTest;
import test.main.runners.TaskRepositoryTestRunner;
import test.main.utils.TestUtils;

public class Config {
    
    // Faz a configuração de um DatabaseManager
    static DatabaseManager createDatabaseManager() {
        DatabaseProperties databaseProperties = new DatabaseProperties(Paths.PATH, Paths.COMPLETE_PATH);
        ConfigurationService configurationService = new ConfigurationServiceImpl(databaseProperties);
        return new DatabaseManager(configurationService);
    }

    // Faz a configuração do TaskRepositoryTestRunner
    static TaskRepositoryTestRunner createTaskRepositoryTestRunner(DatabaseManager databaseManager) {
        TestUtils testUtils = new TestUtils();
        TaskRepository repository = new TaskRepositoryImpl(databaseManager);
        TaskRepositoryTest test = new TaskRepositoryTest(repository, testUtils);
        return new TaskRepositoryTestRunner(test);
    }

}
