package main.ui;

import static main.ui.screens.utils.ScannerUtils.clearBuffer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.controller.DatabaseController;
import main.controller.TaskController;
import main.exceptions.FileCannotBeReadException;
import main.exceptions.InvalidCodeException;
import main.exceptions.InvalidInputException;
import main.exceptions.TaskNotFoundException;
import main.exceptions.response.ExceptionResponse;
import main.model.entities.Task;
import main.model.enums.TaskStatus;

// Classe responsável pela interface do usuário
public class UserInterface {
    
    private DatabaseController databaseController;
    private TaskController controller;
    private UiTemplateBuilder builder;

    public UserInterface(DatabaseController databaseController, TaskController controller, UiTemplateBuilder builder) {
        this.databaseController = databaseController;
        this.controller = controller;
        this.builder = builder;
    }

    public void run() {

        Scanner input = new Scanner(System.in);

        int option = -1;
        Task task;
        List<Task> list;
        Map<String, String> properties;
        String NO_TASKS = "Nenhuma tarefa encontrada";

        System.out.println(builder.buildTitle());

        while(option != 0) {
            try {
                System.out.print(builder.buildMainMenuScreen());
                option = input.nextInt();
                clearBuffer(input);
                System.out.println();
                switch (option) {
                    // Encerramento do programa
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    // Criar uma tarefa
                    case 1:
                        task = new Task();
                        System.out.print("Digite a descrição da tarefa: ");
                        task.setDescription(input.nextLine());
                        System.out.println("Defina o status da tarefa: ");
                        System.out.print(builder.buildTaskStatusMenu());
                        task.setStatus(TaskStatus.fromCode(input.nextInt()));
                        clearBuffer(input);
                        task = controller.createTask(task);
                        System.out.println();
                        System.out.println(task);
                        System.out.println("Tarefa criada com sucesso!");
                        break;
                    // Atualizar uma tarefa
                    case 2:
                        System.out.print("Digite o id da tarefa: ");
                        task = controller.findTaskById(input.nextLong());
                        clearBuffer(input);
                        System.out.print("Digite a descrição da tarefa: ");
                        task.setDescription(input.nextLine());
                        System.out.println("Defina o status da tarefa: ");
                        System.out.print(builder.buildTaskStatusMenu());
                        task.setStatus(TaskStatus.fromCode(input.nextInt()));
                        task = controller.updateTask(task);
                        System.out.println();
                        System.out.println(task);
                        System.out.println("Tarefa atualizada com sucesso!");
                        break;
                    // Excluir uma tarefa
                    case 3:
                        System.out.print("Digite o id da tarefa: ");
                        controller.deleteTask(input.nextLong());
                        clearBuffer(input);
                        System.out.println();
                        System.out.println("Tarefa excluida com sucesso!");
                        break;
                    // Buscar uma tarefa pelo id
                    case 4:
                        System.out.print("Digite o id da tarefa: ");
                        task = controller.findTaskById(input.nextLong());
                        clearBuffer(input);
                        System.out.println();
                        System.out.print(task);
                        break;
                    // Buscar todas as tarefas cadastradas
                    case 5:
                        System.out.println("Todas as tarefas cadastradas: ");
                        System.out.println();
                        list = controller.findAllTasks();
                        if(list.isEmpty()) System.out.println(NO_TASKS);
                        list.forEach(System.out::println);
                        break;
                    // Buscar todas as tarefas a fazer
                    case 6:
                        System.out.println("Todas as tarefas a fazer: ");
                        System.out.println();
                        list = controller.findAllTasksTodo();
                        if(list.isEmpty()) System.out.println(NO_TASKS);
                        list.forEach(System.out::println);
                        break;
                    // Buscar todas as tarefas em progresso
                    case 7:
                        System.out.println("Todas as tarefas em progresso: ");
                        System.out.println();
                        list = controller.findAllTasksInProgress();
                        if(list.isEmpty()) System.out.println(NO_TASKS);
                        list.forEach(System.out::println);
                        break;
                    // Buscar todas as tarefas concluídas
                    case 8:
                        System.out.println("Todas as tarefas concluídas: ");
                        System.out.println();
                        list = controller.findAllTasksDone();
                        if(list.isEmpty()) System.out.println(NO_TASKS);
                        list.forEach(System.out::println);
                        break;
                    case 9:
                        System.out.println("Configurações atuais do banco de dados:");
                        System.out.println();
                        properties = databaseController.getDatabaseProperties();
                        properties.forEach((key, value) -> System.out.println(key + value));
                        break;
                    case 10:
                        System.out.println("Configurações atuais do banco de dados:");
                        System.out.println();
                        properties = databaseController.getDatabaseProperties();
                        properties.forEach((key, value) -> System.out.println(key + value));
                        System.out.println();
                        System.out.print("Digite o novo nome do banco de dados (Deixe em branco para usar o padrão): ");
                        String databaseName = input.nextLine();
                        System.out.print("Digite o caminho do banco de dados (Deixe em branco para usar o padrão): ");
                        String databasePath = input.nextLine();
                        databaseController.configureDatabase(databaseName, databasePath);
                        System.out.println("Banco de dados configurado com sucesso!");
                        break;
                    default:
                        System.out.println(new ExceptionResponse(new InvalidCodeException()));
                }
                System.out.println();
            } catch(Exception e) {
                System.out.println();
                Exception exception = e;
                if(e.getClass() == InputMismatchException.class) exception = new InvalidInputException("Entrada inválida");
                if(e.getClass() == IllegalArgumentException.class) exception = new InvalidCodeException("Código inválido");
                if(e.getClass() == TaskNotFoundException.class) exception = new TaskNotFoundException("Tarefa não encontrada");
                if(e.getClass() == ArrayIndexOutOfBoundsException.class) exception = new FileCannotBeReadException("Não foi possível ler o arquivo json");
                System.out.println(new ExceptionResponse(e, exception));
                clearBuffer(input);
            }
        }

        input.close();

    }

}
