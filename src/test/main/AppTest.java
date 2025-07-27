package test.main;

import test.main.config.AppTestConfig;
import test.main.config.TestRunner;

public class AppTest {
    public static void main(String[] args) {
        TestRunner runner = new TestRunner(new AppTestConfig());
        runner.run();
    }
}
