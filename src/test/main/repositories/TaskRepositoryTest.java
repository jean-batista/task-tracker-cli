package test.main.repositories;

import static test.main.assertions.TestAssertions.*;

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
    
    public void save() {
        beforeEach();

        Task created = repository.save(task);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertNotNull(created.getDescription());
        assertNotNull(created.getStatus());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());

        assertEquals(1L, created.getId());
        assertEquals("test01", created.getDescription());
        assertEquals(TaskStatus.TODO, created.getStatus());

        System.out.println("TaskRepository save method is ok");
    }

    public void update() {
        beforeEach();

        Task created = repository.save(task);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertNotNull(created.getDescription());
        assertNotNull(created.getStatus());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());

        utils.sleep(2000);
        Task updated = repository.update(utils.createTaskEntity(created.getId(), "test01-updated", TaskStatus.IN_PROGRESS));

        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertNotNull(updated.getDescription());
        assertNotNull(updated.getStatus());
        assertNotNull(updated.getCreatedAt());
        assertNotNull(updated.getUpdatedAt());

        assertEquals(created.getId(), updated.getId());
        assertEquals("test01-updated", updated.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, updated.getStatus());
        assertEquals(created.getCreatedAt(), updated.getCreatedAt());

        assertNotEquals(created.getUpdatedAt(), updated.getUpdatedAt());

        System.out.println("TaskRepository update method is ok");
    }

    public void delete() {
        System.out.println("testDeleteMethod is not implemented");
    }

    public void findById() {
        System.out.println("testFindByIdMethod is not implemented");
    }

    public void findAll() {
        System.out.println("testFindAllMethod is not implemented");
    }

    public void findAllTodo() {
        System.out.println("testFindAllTodoMethod is not implemented");
    }

    public void findAllInProgress() {
        System.out.println("testFindAllInProgressMethod is not implemented");
    }

    public void findAllDone() {
        System.out.println("testFindAllDoneMethod is not implemented");
    }

}
