package main.ui.screens;

import java.util.Arrays;
import java.util.List;

import static main.ui.screens.utils.ScreenUtils.*;

// Classe responsável por construir a tela de menu
public class MainMenuScreen {
    
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
            "Por favor, escolha umas das opções abaixo: ",
            "",
            "Opção 00: Sair",
            "Opção 01: Criar uma tarefa",
            "Opção 02: Atualizar uma tarefa",
            "Opção 03: Excluir uma tarefa",
            "Opção 04: Buscar uma tarefa pelo id",
            "Opção 05: Buscar todas as tarefas",
            "Opção 06: Buscar todas as tarefas a fazer",
            "Opção 07: Buscar todas as tarefas em progresso",
            "Opção 08: Buscar todas as tarefas concluídas",
            "Opção 09: Ver configurações do banco de dados",
            "Opção 10: Alterar configurações do arquivo de dados"
        );
    }

}
