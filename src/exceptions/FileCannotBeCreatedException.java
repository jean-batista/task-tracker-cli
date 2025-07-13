package exceptions;

public class FileCannotBeCreatedException extends RuntimeException {
    public FileCannotBeCreatedException() {
        super("The file cannot be created");
    }
}
