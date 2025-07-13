package exceptions;

public class FileIsEmptyException extends RuntimeException {
    public FileIsEmptyException() {
        super("The file is empty");
    }
}
