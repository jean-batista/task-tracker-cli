package test.main.repositories;

import static test.main.assertions.TestAssertions.*;

import java.util.List;

import main.exceptions.TaskNotFoundException;
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
        Task updated = repository.update(
            new Task(created.getId(), "test01-updated", TaskStatus.IN_PROGRESS, null, null)
        );

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

    public void findById() {
        beforeEach();

        Task created = repository.save(task);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertNotNull(created.getDescription());
        assertNotNull(created.getStatus());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());

        Task finded = repository.findById(created.getId()).orElseThrow(TaskNotFoundException::new);

        assertNotNull(finded);
        assertNotNull(finded.getId());
        assertNotNull(finded.getDescription());
        assertNotNull(finded.getStatus());
        assertNotNull(finded.getCreatedAt());
        assertNotNull(finded.getUpdatedAt());

        assertEquals(created.getId(), finded.getId());
        assertEquals(created.getDescription(), finded.getDescription());
        assertEquals(created.getStatus(), finded.getStatus());
        assertEquals(created.getCreatedAt(), finded.getCreatedAt());
        assertEquals(created.getUpdatedAt(), finded.getUpdatedAt());

        System.out.println("TaskRepository findById method is ok");
    }

    public void findAll() {
        beforeEach();

        List<Task> list = utils.createTaskList(9, "test");

        for(Task created : list) {
            task = repository.save(created);

            assertNotNull(created);
            assertNotNull(created.getId());
            assertNotNull(created.getDescription());
            assertNotNull(created.getStatus());
            assertNotNull(created.getCreatedAt());
            assertNotNull(created.getUpdatedAt());
        }

        list = repository.findAll();

        for(int i = 0; i < list.size(); i++) {
            assertNotNull(list.get(i));
            assertNotNull(list.get(i).getId());
            assertNotNull(list.get(i).getDescription());
            assertNotNull(list.get(i).getStatus());
            assertNotNull(list.get(i).getCreatedAt());
            assertNotNull(list.get(i).getUpdatedAt());

            assertEquals(Long.valueOf(i + 1), list.get(i).getId());
            assertEquals("test" + (i + 1), list.get(i).getDescription());
        }

        System.out.println("TaskRepository findAll method is ok");
    }

    public void findAllTodo() {
        beforeEach();

        List<Task> list = utils.createTaskList(9, "test");

        for(Task created : list) {
            task = repository.save(created);

            assertNotNull(created);
            assertNotNull(created.getId());
            assertNotNull(created.getDescription());
            assertNotNull(created.getStatus());
            assertNotNull(created.getCreatedAt());
            assertNotNull(created.getUpdatedAt());
        }

        list = repository.findAllTasksTodo();

        for(Task finded : list) {
            assertNotNull(finded);
            assertNotNull(finded.getId());
            assertNotNull(finded.getDescription());
            assertNotNull(finded.getStatus());
            assertNotNull(finded.getCreatedAt());
            assertNotNull(finded.getUpdatedAt());

            assertEquals(TaskStatus.TODO, finded.getStatus());
        }

        System.out.println("TaskRepository findAllTodo method is ok");
    }

    public void findAllInProgress() {
        beforeEach();

        List<Task> list = utils.createTaskList(9, "test");

        for(Task created : list) {
            task = repository.save(created);

            assertNotNull(created);
            assertNotNull(created.getId());
            assertNotNull(created.getDescription());
            assertNotNull(created.getStatus());
            assertNotNull(created.getCreatedAt());
            assertNotNull(created.getUpdatedAt());
        }

        list = repository.findAllTasksInProgress();

        for(Task finded : list) {
            assertNotNull(finded);
            assertNotNull(finded.getId());
            assertNotNull(finded.getDescription());
            assertNotNull(finded.getStatus());
            assertNotNull(finded.getCreatedAt());
            assertNotNull(finded.getUpdatedAt());

            assertEquals(TaskStatus.IN_PROGRESS, finded.getStatus());
        }

        System.out.println("TaskRepository findAllInProgress method is ok");
    }

    public void findAllDone() {
        beforeEach();

        List<Task> list = utils.createTaskList(9, "test");

        for(Task created : list) {
            task = repository.save(created);

            assertNotNull(created);
            assertNotNull(created.getId());
            assertNotNull(created.getDescription());
            assertNotNull(created.getStatus());
            assertNotNull(created.getCreatedAt());
            assertNotNull(created.getUpdatedAt());
        }

        list = repository.findAllTasksDone();

        for(Task finded : list) {
            assertNotNull(finded);
            assertNotNull(finded.getId());
            assertNotNull(finded.getDescription());
            assertNotNull(finded.getStatus());
            assertNotNull(finded.getCreatedAt());
            assertNotNull(finded.getUpdatedAt());

            assertEquals(TaskStatus.DONE, finded.getStatus());
        }

        System.out.println("TaskRepository findAllDone method is ok");
    }

    public void delete() {
        beforeEach();

        Task created = repository.save(task);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertNotNull(created.getDescription());
        assertNotNull(created.getStatus());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());

        List<Task> list = repository.findAll();

        assertNotNull(list);
        assertTrue(list.size() > 0);

        for(Task finded : list) {
            assertNotNull(finded);
            assertNotNull(finded.getId());
            assertNotNull(finded.getDescription());
            assertNotNull(finded.getStatus());
            assertNotNull(finded.getCreatedAt());
            assertNotNull(finded.getUpdatedAt());
        }

        repository.delete(1L);

        list = repository.findAll();

        assertNotNull(list);

        assertTrue(list.size() == 0);

        System.out.println("TaskRepository delete method is ok");
    }

}
