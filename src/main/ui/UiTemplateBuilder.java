package main.ui;

import main.ui.screens.MainMenuScreen;
import main.ui.screens.TaskStatusMenu;
import main.ui.screens.Title;

public class UiTemplateBuilder {
    
    private Title title;
    private MainMenuScreen mainMenuScreen;
    private TaskStatusMenu taskStatusMenu;

    public UiTemplateBuilder(Title title, MainMenuScreen mainMenuScreen, TaskStatusMenu taskStatusMenu) {
        this.title = title;
        this.mainMenuScreen = mainMenuScreen;
        this.taskStatusMenu = taskStatusMenu;
    }

    public String buidTitle() {
        return title.build();
    }

    public String buildMainMenuScreen() {
        return mainMenuScreen.build();
    }

    public String buildTaskStatusMenu() {
        return taskStatusMenu.build();
    }

}
