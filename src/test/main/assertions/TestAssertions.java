package test.main.assertions;

import java.util.Objects;

import test.main.exceptions.AssertionErrorException;

public class TestAssertions {
    
    public static void assertNotNull(Object object) {
        if(object == null) throw new AssertionErrorException("Value is null");
    }

    public static void assertEquals(Object value, Object object) {
        if(!(Objects.equals(value, object))) error(value, object, "Unexpected value");
    }

    public static void assertNotEquals(Object value, Object object) {
        if(Objects.equals(value, object)) error(value, object, "Equal values");
    }

    public static void assertTrue(boolean condiction) {
        if(!condiction) throw new AssertionErrorException("Condition is false");
    }

    private static void error(Object expected, Object actual, String message) {
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
        throw new AssertionErrorException(message);
    }

}
