package test.main.utils;

import java.io.File;

import test.main.exceptions.AssertionErrorException;

public class TestUtils {

    public void assertNotNull(Object object) {
        if(object == null) throw new AssertionErrorException("Value is null");
    }

    public void assertEquals(Object value, Object object) {
        if(!(value == object)) error(value, object, "Unexpected value");
    }

    public void error(Object actual, Object expected, String message) {
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
        throw new AssertionErrorException(message);
    }

    public void deleteTestDatabase() {
        File file = new File("src/test/resources/database/databasetest.json");
        file.delete();
    }

}
