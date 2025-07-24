package test.main.runners;

import test.main.repositories.TaskRepositoryTest;

public class TaskRepositoryTestRunner {
    
    private TaskRepositoryTest taskRepositoryTest;

    public TaskRepositoryTestRunner(TaskRepositoryTest taskRepositoryTest) {
        this.taskRepositoryTest = taskRepositoryTest;
    }

    public void run() {
        taskRepositoryTest.testSaveMethod();
        taskRepositoryTest.testUpdateMethod();
        taskRepositoryTest.testDeleteMethod();
        taskRepositoryTest.testFindByIdMethod();
        taskRepositoryTest.testFindAllMethod();
        taskRepositoryTest.testFindAllTodoMethod();
        taskRepositoryTest.testFindAllInProgressMethod();
        taskRepositoryTest.testFindAllDoneMethod();
    }

}
