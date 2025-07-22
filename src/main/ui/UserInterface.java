package main.ui;

import java.util.Scanner;

import main.controller.TaskController;
import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.ui.utils.UserInterfaceUtils;
import main.utils.ScannerUtils;

public class UserInterface {
    
    private TaskController controller;

    public UserInterface(TaskController controller) {
        this.controller = controller;
    }

    public void run() {

        Scanner input = new Scanner(System.in);
        UserInterfaceUtils utils = new UserInterfaceUtils();
        ScannerUtils scannerUtils = new ScannerUtils();

        int option = -1;
        Task task;

        System.out.println("Bem vindo ao gerenciador de tarefas!");
        System.out.println("Por favor, escolha uma das opções abaixo: ");
        System.out.println();

        while(option != 0) {
            task = new Task();
            utils.menu();
            option = input.nextInt();
            scannerUtils.clearBuffer(input);
            System.out.println();
            switch (option) {
                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    task.setDescription(input.nextLine());
                    System.out.println("Defina o status da tarefa: ");
                    utils.taskStatusMenu();
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
                    utils.taskStatusMenu();
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
                    controller.findAllTasks().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Not implemented");
                    break;
                case 7:
                    System.out.println("Not implemented");
                    break;
                case 8:
                    System.out.println("Not implemented");
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
