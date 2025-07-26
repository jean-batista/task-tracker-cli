package main.config;

import main.controller.TaskController;
import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.services.TaskService;
import main.services.impl.TaskServiceImpl;
import main.ui.UiTemplateBuilder;
import main.ui.UserInterface;
import main.utils.FileUtils;

public class AppConfig {
    
    public UserInterface createUserInterface() {
        FileUtils fileUtils = new FileUtils();
        TaskRepository repository = new TaskRepositoryImpl(fileUtils);
        TaskService service = new TaskServiceImpl(repository);
        TaskController controller = new TaskController(service);
        UiTemplateBuilder builder = new UiTemplateBuilderConfig().createUiTemplateBuilder();
        return new UserInterface(controller, builder);
    }

}
