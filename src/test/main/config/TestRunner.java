package test.main.config;

public class TestRunner {

    private AppTestConfig config;

    public TestRunner(AppTestConfig config) {
        this.config = config;
    }
    
    // Executa todos os testes
    public void run() {
        config.createTaskRepositoryTestRunner().run();
    }

}
