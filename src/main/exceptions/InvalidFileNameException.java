package main.exceptions;

public class InvalidFileNameException extends RuntimeException {
    public InvalidFileNameException() {
        super("The file name is invalid");
    }

    public InvalidFileNameException(String message) {
        super(message);
    }
}
