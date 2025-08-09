package main.ui;

import java.util.List;
import java.util.Scanner;

import main.controller.TaskController;
import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.utils.ScannerUtils;

public class UserInterface {
    
    private TaskController controller;
    private UiTemplateBuilder builder;

    public UserInterface(TaskController controller, UiTemplateBuilder builder) {
        this.controller = controller;
        this.builder = builder;
    }

    public void run() {

        Scanner input = new Scanner(System.in);
        ScannerUtils scannerUtils = new ScannerUtils();

        String title = builder.buidTitle();
        String mainMenu = builder.buildMainMenuScreen();
        String taskStatusMenu = builder.buildTaskStatusMenu();

        int option = -1;
        Task task;
        List<Task> list;
        String noTasks = "Nenhuma tarefa encontrada";

        System.out.println(title);

        while(option != 0) {
            task = new Task();
            System.out.print(mainMenu);
            option = input.nextInt();
            scannerUtils.clearBuffer(input);
            System.out.println();
            switch (option) {
                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    task.setDescription(input.nextLine());
                    System.out.println("Defina o status da tarefa: ");
                    System.out.print(taskStatusMenu);
                    task.setStatus(TaskStatus.fromCode(input.nextInt()));
                    scannerUtils.clearBuffer(input);
                    task = controller.createTask(task);
                    System.out.println();
                    System.out.println(task);
                    System.out.println("Tarefa criada com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o id da tarefa: ");
                    task.setId(input.nextLong());
                    scannerUtils.clearBuffer(input);
                    System.out.print("Digite a descrição da tarefa: ");
                    task.setDescription(input.nextLine());
                    System.out.println("Defina o status da tarefa: ");
                    System.out.print(taskStatusMenu);
                    task.setStatus(TaskStatus.fromCode(input.nextInt()));
                    task = controller.updateTask(task);
                    System.out.println();
                    System.out.println(task);
                    System.out.println("Tarefa atualizada com sucesso!");
                    break;
                case 3:
                    System.out.print("Digite o id da tarefa: ");
                    controller.deleteTask(input.nextLong());
                    scannerUtils.clearBuffer(input);
                    System.out.println();
                    System.out.println("Tarefa excluida com sucesso!");
                    break;
                case 4:
                    System.out.print("Digite o id da tarefa: ");
                    task = controller.findTaskById(input.nextLong());
                    scannerUtils.clearBuffer(input);
                    System.out.println();
                    System.out.print(task);
                    break;
                case 5:
                    System.out.println("Todas as tarefas cadastradas: ");
                    System.out.println();
                    list = controller.findAllTasks();
                    if(list.isEmpty()) {
                        System.out.println(noTasks);
                        break;
                    }
                    list.forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Todas as tarefas a fazer: ");
                    System.out.println();
                    list = controller.findAllTasksTodo();
                    if(list.isEmpty()) {
                        System.out.println(noTasks);
                        break;
                    }
                    list.forEach(System.out::println);
                    break;
                case 7:
                    System.out.println("Todas as tarefas em progresso: ");
                    System.out.println();
                    list = controller.findAllTasksInProgress();
                    if(list.isEmpty()) {
                        System.out.println(noTasks);
                        break;
                    }
                    list.forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("Todas as tarefas concluídas");
                    System.out.println();
                    list = controller.findAllTasksDone();
                    if(list.isEmpty()) {
                        System.out.println(noTasks);
                        break;
                    }
                    list.forEach(System.out::println);
                    break;
                default:
                    if(option != 0) System.out.println("Entrada inválida, tente novamente");
                    System.out.println();
            }
            System.out.println();
        }

        input.close();

    }

}
