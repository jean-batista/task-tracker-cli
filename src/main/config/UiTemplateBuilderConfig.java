package main.config;

import main.ui.UiTemplateBuilder;
import main.ui.screens.MainMenuScreen;
import main.ui.screens.TaskStatusMenu;
import main.ui.screens.Title;

public class UiTemplateBuilderConfig {
    
    public UiTemplateBuilder createUiTemplateBuilder() {
        Title title = new Title();
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        TaskStatusMenu taskStatusMenu = new TaskStatusMenu();
        UiTemplateBuilder builder = new UiTemplateBuilder(title, mainMenuScreen, taskStatusMenu);
        return builder;
    }

}
