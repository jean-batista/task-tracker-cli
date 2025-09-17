package test.main.config;

import main.database.DatabaseManager;
import test.main.runners.TaskRepositoryTestRunner;

public class AppTestConfig {

    // Realiza a injeção de dependências
    TaskRepositoryTestRunner createTaskRepositoryTestRunner() {
        DatabaseManager databaseManager = Config.createDatabaseManager();
        return Config.createTaskRepositoryTestRunner(databaseManager);
    }

}
