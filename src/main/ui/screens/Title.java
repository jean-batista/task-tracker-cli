package main.ui.screens;

import static main.ui.screens.utils.ScreenUtils.*;

public class Title {
    
    public String build() {
        String breaker = "\n";

        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(dash(60)).append(breaker);
        sb.append("|").append(blank(60)).append("|").append(breaker);
        sb.append("|").append(blank(12))
            .append("Bem vindo ao gerenciador de tarefas")
            .append(blank(13)).append("|").append("\n");
        sb.append("|").append(blank(60)).append("|").append(breaker);
        sb.append(" ").append(dash(60));
        return sb.toString();
    }

}
