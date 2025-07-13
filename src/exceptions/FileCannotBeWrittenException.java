package exceptions;

public class FileCannotBeWrittenException extends RuntimeException {
    public FileCannotBeWrittenException() {
        super("The file cannot be written");
    }
}
