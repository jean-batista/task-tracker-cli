package main.config;

import main.controller.DatabaseController;
import main.controller.TaskController;
import main.services.ConfigurationService;
import main.ui.UiTemplateBuilder;
import main.ui.UserInterface;

public class AppConfig {

    // Injeta todas as dependências necessárias para a aplicação
    public UserInterface createUserInterface() {
        ConfigurationService configurationService = Config.creatConfigurationService();
        DatabaseController databaseController = Config.createDatabaseConfig(configurationService);
        TaskController taskController = Config.createTaskConfig(configurationService);
        UiTemplateBuilder uiTemplateBuilder = Config.createUiTemplateBuilderConfig();
        return new UserInterface(databaseController, taskController, uiTemplateBuilder);
    } 

}
