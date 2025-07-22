package main;

import main.config.AppConfig;
import main.ui.UserInterface;

public class App {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        UserInterface userInterface = config.createUserInterface();
        userInterface.run();
    }
}
