package test.main.runners;

import test.main.repositories.TaskRepositoryTest;

// Classe responsável por executar todos os métodos do TaskRepositoryTest
public class TaskRepositoryTestRunner {
    
    private TaskRepositoryTest taskRepositoryTest;

    public TaskRepositoryTestRunner(TaskRepositoryTest taskRepositoryTest) {
        this.taskRepositoryTest = taskRepositoryTest;
    }

    // Executa todos os métodos de teste
    public void run() {
        taskRepositoryTest.save();
        taskRepositoryTest.update();
        taskRepositoryTest.delete();
        taskRepositoryTest.findById();
        taskRepositoryTest.findAll();
        taskRepositoryTest.findAllTodo();
        taskRepositoryTest.findAllInProgress();
        taskRepositoryTest.findAllDone();
    }

}
