package main.ui.screens;

import java.util.Arrays;
import java.util.List;

import static main.ui.screens.utils.ScreenUtils.*;

// Classe responsável por construir a tela escolha do status da Task
public class TaskStatusMenu {
    
    public String build() {
        String breaker = "\n";
        int leftMargin = 5;
        int rightMargin;

        StringBuilder sb = new StringBuilder();
        sb.append(" ").append(dash(60)).append(breaker);
        for(String option : options()) {
            rightMargin = 60 - (option.length() + leftMargin);
            sb.append("|").append(blank(leftMargin))
                .append(option).append(blank(rightMargin))
                .append("|").append(breaker);
        }
        sb.append(" ").append(dash(60)).append(breaker);
        sb.append(breaker).append("Opção: ");
        return sb.toString();
    }

    private List<String> options() {
        return Arrays.asList(
            "Opção 01: A fazer",
            "Opção 02: Em progresso",
            "Opção 03: Concluído"  
        );
    }

}
