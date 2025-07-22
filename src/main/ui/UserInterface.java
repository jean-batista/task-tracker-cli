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

        System.out.println("Bem vindo ao rastreador de tarefas!");
        System.out.println("Por favor, escolha uma das opções abaixo: ");

        while(option != 0) {
            utils.menu();
            option = input.nextInt();
            scannerUtils.clearBuffer(input);
            switch (option) {
                case 1:
                    task = new Task();
                    System.out.print("Digite a descrição da tarefa: ");
                    task.setDescription(input.nextLine());
                    System.out.println("Defina o status da tarefa: ");
                    utils.taskStatusMenu();
                    option = input.nextInt();
                    task.setStatus(TaskStatus.fromCode(option));
                    scannerUtils.clearBuffer(input);
                    task = controller.createTask(task);
                    System.out.println();
                    System.out.println(task);
                    System.out.println("Tarefa criada com sucesso!");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Not implemented");
                    break;
                case 3:
                    System.out.println("Not implemented");
                    break;
                case 4:
                    System.out.println("Not implemented");
                    break;
                case 5:
                    System.out.println("Not implemented");
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
            }
        }

        input.close();

    }

}
