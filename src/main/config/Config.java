package main.config;

import main.controller.DatabaseController;
import main.controller.TaskController;
import main.database.DatabaseManager;
import main.database.DatabaseProperties;
import main.repositories.TaskRepository;
import main.repositories.impl.TaskRepositoryImpl;
import main.services.ConfigurationService;
import main.services.DatabaseService;
import main.services.TaskService;
import main.services.impl.ConfigurationServiceImpl;
import main.services.impl.DatabaseServiceImpl;
import main.services.impl.TaskServiceImpl;
import main.ui.UiTemplateBuilder;
import main.ui.screens.MainMenuScreen;
import main.ui.screens.TaskStatusMenu;
import main.ui.screens.Title;

public class Config {

    // Método para criar e configurar o ConfigurationService
    static ConfigurationService creatConfigurationService() {
        DatabaseProperties properties = new DatabaseProperties();
        return new ConfigurationServiceImpl(properties);
    }

    // Método para criar e configurar o DatabaseController
    // Injeta todas as dependências necessárias para o DatabaseController
    static DatabaseController createDatabaseConfig(ConfigurationService configurationService) {
        DatabaseService service = new DatabaseServiceImpl(configurationService);
        return new DatabaseController(service);
    }

    // Método para criar e configurar o TaskController
    // Injeta todas as dependências necessárias para o TaskController
    static TaskController createTaskConfig(ConfigurationService configurationService) {
        DatabaseManager manager = new DatabaseManager(configurationService);
        TaskRepository repository = new TaskRepositoryImpl(manager);
        TaskService service = new TaskServiceImpl(repository);
        return new TaskController(service);
    }

    // Método para criar e configurar o UiTemplateBuilder
    // Injeta todas as dependências necessárias para o UiTemplateBuilder
    static UiTemplateBuilder createUiTemplateBuilderConfig() {
        Title title = new Title();
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        TaskStatusMenu taskStatusMenu = new TaskStatusMenu();
        UiTemplateBuilder builder = new UiTemplateBuilder(title, mainMenuScreen, taskStatusMenu);
        return builder;
    }
    
}
