package main.exceptions;

public class FileCannotBeReadException extends RuntimeException {
    public FileCannotBeReadException(String message) {
        super(message);
    }

    public FileCannotBeReadException() {
        super("The file cannot be read");
    }
}
