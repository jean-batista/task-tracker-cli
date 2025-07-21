package main.config;

import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.services.impl.TaskServiceImpl;
import main.utils.FileUtils;

public class AppConfig {
    
    public TaskServiceImpl createTaskService() {
        FileUtils fileUtils = new FileUtils();
        TaskRepository repository = new TaskRepositoryImpl(fileUtils);
        return new TaskServiceImpl(repository);
    }

}
