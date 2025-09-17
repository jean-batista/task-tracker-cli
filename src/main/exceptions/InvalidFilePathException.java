package main.exceptions;

public class InvalidFilePathException extends RuntimeException {
    public InvalidFilePathException() {
        super("The path is invalid");
    }

    public InvalidFilePathException(String message) {
        super(message);
    }
}
