package main.exceptions;

public class FileCannotBeReadException extends RuntimeException {
    public FileCannotBeReadException() {
        super("The file cannot be read");
    }
}
