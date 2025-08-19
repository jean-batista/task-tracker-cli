package main.exceptions;

public class InvalidCodeException extends RuntimeException {
    public InvalidCodeException() {
        super("Invalid code");
    }

    public InvalidCodeException(String message) {
        super(message);
    }
}
