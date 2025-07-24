package test.main.runners;

import test.main.repositories.TaskRepositoryTest;

public class TaskRepositoryTestRunner {
    
    private TaskRepositoryTest taskRepositoryTest;

    public TaskRepositoryTestRunner(TaskRepositoryTest taskRepositoryTest) {
        this.taskRepositoryTest = taskRepositoryTest;
    }

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
