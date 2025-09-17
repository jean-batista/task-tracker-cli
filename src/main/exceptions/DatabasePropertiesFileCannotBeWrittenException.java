package main.exceptions;

public class DatabasePropertiesFileCannotBeWrittenException extends RuntimeException {
    public DatabasePropertiesFileCannotBeWrittenException() {
        super("Database properties file cannot be wrriten");
    }

    public DatabasePropertiesFileCannotBeWrittenException(String message) {
        super(message);
    }
}
