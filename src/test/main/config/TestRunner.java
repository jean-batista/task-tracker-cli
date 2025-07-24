package test.main.config;

public class TestRunner {

    private AppTestConfig config;

    public TestRunner(AppTestConfig config) {
        this.config = config;
    }
    
    public void run() {
        config.createTaskRepositoryTestRunner().run();
    }

}
