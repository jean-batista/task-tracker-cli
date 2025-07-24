package test.main.repositories;

import main.model.entities.Task;
import main.model.enums.TaskStatus;
import main.repositories.TaskRepository;
import test.main.utils.TestUtils;

public class TaskRepositoryTest {

    private TestUtils utils;
    private TaskRepository repository;

    public TaskRepositoryTest(TaskRepository repository, TestUtils utils) {
        this.repository = repository;
        this.utils = utils;
    }

    Task task;

    private void beforeEach() {
        utils.deleteTestDatabase();
        task = new Task(null, "test01", TaskStatus.TODO, null, null);
    }
    
    public void testSaveMethod() {
        beforeEach();

        Task created = repository.save(task);

        utils.assertNotNull(created.getId());
        utils.assertNotNull(created.getDescription());
        utils.assertNotNull(created.getStatus());
        utils.assertNotNull(created.getCreatedAt());
        utils.assertNotNull(created.getUpdatedAt());

        utils.assertEquals(1L, created.getId());
        utils.assertEquals("test01", created.getDescription());
        utils.assertEquals(TaskStatus.TODO, created.getStatus());

        System.out.println("TaskRepository create method is ok");
    }

    public void testUpdateMethod() {
        System.out.println("testUpdateMethod is not implemented");
    }

    public void testDeleteMethod() {
        System.out.println("testDeleteMethod is not implemented");
    }

    public void testFindByIdMethod() {
        System.out.println("testFindByIdMethod is not implemented");
    }

    public void testFindAllMethod() {
        System.out.println("testFindAllMethod is not implemented");
    }

    public void testFindAllTodoMethod() {
        System.out.println("testFindAllTodoMethod is not implemented");
    }

    public void testFindAllInProgressMethod() {
        System.out.println("testFindAllInProgressMethod is not implemented");
    }

    public void testFindAllDoneMethod() {
        System.out.println("testFindAllDoneMethod is not implemented");
    }

}
