package main.exceptions;

public class DatabasePropertiesFileCannotBeReadException extends RuntimeException {
    public DatabasePropertiesFileCannotBeReadException() {
        super("Database properties file cannot be read");
    }

    public DatabasePropertiesFileCannotBeReadException(String message) {
        super(message);
    }
}
