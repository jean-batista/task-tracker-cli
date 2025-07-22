package main.ui.utils;

public class UserInterfaceUtils {
    
    public void menu() {
        System.out.println("Opção 00: Sair");
        System.out.println("Opção 01: Criar uma tarefa");
        System.out.println("Opção 02: Atualizar uma tarefa");
        System.out.println("Opção 03: Excluir uma tarefa");
        System.out.println("Opção 04: Buscar uma tarefa pelo id");
        System.out.println("Opção 05: Buascar todas as tarefas");
        System.out.println("Opção 06: Buscar todas as tarefas a fazer");
        System.out.println("Opção 07: Bucas todas as tarefas em progresso");
        System.out.println("Opção 08: Buscar todas as tarefas concluídas");
        System.out.print("Opção: ");
    }

    public void taskStatusMenu() {
        System.out.println("Opção 01: A fazer");
        System.out.println("Opção 02: Em progresso");
        System.out.println("Opção 03: Concluído");
        System.out.print("Opção: ");
    }

}
