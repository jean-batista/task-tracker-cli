package test.main.assertions;

import java.util.Objects;

import test.main.exceptions.AssertionErrorException;

// Classe responsável por verificações
public class TestAssertions {
    
    // Verifica se um objeto não é nulo
    public static void assertNotNull(Object object) {
        if(object == null) throw new AssertionErrorException("Value is null");
    }

    // Verifica se dois objetos são iguais
    public static void assertEquals(Object value, Object object) {
        if(!(Objects.equals(value, object))) error(value, object, "Unexpected value");
    }

    // Verifica se dois objetos não são iguais
    public static void assertNotEquals(Object value, Object object) {
        if(Objects.equals(value, object)) error(value, object, "Equal values");
    }

    // Verifica se uma afirmação é verdadeira
    public static void assertTrue(boolean condiction) {
        if(!condiction) throw new AssertionErrorException("Condition is false");
    }

    // Método responsável por lançar um erro
    private static void error(Object expected, Object actual, String message) {
        // Indica o objeto recebido
        System.out.println("Actual: " + actual);
        // Indica o objeto esperado
        System.out.println("Expected: " + expected);
        throw new AssertionErrorException(message);
    }

}
