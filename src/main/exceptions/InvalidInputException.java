package main.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Entrada inválida");
    }
    
    public InvalidInputException(String message) {
        super(message);
    }
}
