package main.database.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import main.exceptions.DatabasePropertiesFileCannotBeReadException;

// Classe com métodos utilitários
public class DatabasePropertiesUtils {

    // Verifica se um arquivo database.properties existe
    public static boolean verifyIfDatabasePropertiesExists(Path completePath) {
        return Files.exists(completePath);
    }

    // Verifica se um arquivo database.properties esta vazio
    public static boolean verifyIfDatabasePropertiesIsEmpty(Path completePath) {
        try {
            return Files.readAllLines(completePath).isEmpty();
        } catch(Exception e) {
            throw new DatabasePropertiesFileCannotBeReadException("Não foi possível ler o arquivo de propriedades do banco de dados");
        }
    }

    // Cria as propriedades do arquivo database.properties
    public static Properties createDatabaseProperties() {
        Properties properties = new Properties();
        properties.setProperty("database-name", "database.json");
        properties.setProperty("database-path", "src/resources/database");
        properties.setProperty("database-complete-path", "src/resources/database/database.json");
        return properties;
    }

    // Verifica se um arquivo database-test.properties existe
    public static boolean verifyIfDatabaseTestPropertiesExists(Path completePath) {
        return Files.exists(completePath);
    }

    // Verifica se um arquivo database-test.properties esta vazio
    public static boolean verifyIfDatabaseTestPropertiesIsEmpty(Path completePath) {
        try {
            return Files.readAllLines(completePath).isEmpty();
        } catch(Exception e) {
            throw new DatabasePropertiesFileCannotBeReadException("Não foi possível ler o arquivo de propriedades do banco de dados");
        }
    }

    // Cria as propriedades de um arquivo database-test.properties
    public static Properties createDatabaseTestProperties() {
        Properties properties = new Properties();
        properties.setProperty("database-name", "database-test.json");
        properties.setProperty("database-path", "src/test/resources/database");
        properties.setProperty("database-complete-path", "src/test/resources/database/database-test.json");
        return properties;
    }

    // Verifica se um caminho (path) é válido
    public static boolean isValidPath(String path) {
        if(path.contains("..")) return false;
        if(path.contains("../")) return false;
        // Verifica se o arquivo possui uma extensão
        if(path.lastIndexOf("/") < path.lastIndexOf(".")) return false;
        if(path.endsWith("/")) return false;
        if(path.startsWith("/")) return false;
        if(path.contains("//")) return false;
        if(path.contains("\\")) return false;
        return true;
    }

    // Verifica se um nome é válido
    public static boolean isValidName(String name) {
        if(!name.contains(".")) return false;
        if(name.contains("/")) return false;
        if(name.contains("\\")) return false;
        return true;
    }

}
