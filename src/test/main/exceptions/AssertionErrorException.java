package test.main.exceptions;

public class AssertionErrorException extends RuntimeException {
    public AssertionErrorException(String message) {
        super(message);
    }
}
