package main.database.utils;

import main.exceptions.FileCannotBeReadException;

public class JsonFileUtils {
    
    public static void checkJsonFile(String string) {
        // Valida o formato do arquivo JSON

        // Verifica se o arquivo começa com "{" ou "["
        if(string.charAt(0) != '{' && string.charAt(0) != '[') {
            throw new FileCannotBeReadException("Invalid json file");
        }

        // Se o arquivo começa com "{"
        if(string.charAt(0) == '{' ) {

            // verifica se ele termina com "}"
            if(string.charAt(string.length() - 1) != '}') {
                throw new FileCannotBeReadException("Invalid json file");
            }
        }

        // Se o arquivo começa com "["
        if(string.charAt(0) == '[') {

            // verifica se ele termina com "]"
            if(string.charAt(string.length() - 1) != ']') {
                throw new FileCannotBeReadException("Invalid json file");
            }

            // Verifica se o segundo caractere é "{"
            if(string.charAt(1) != '{') {
                throw new FileCannotBeReadException("Invalid json file");
            }

            // Verifica se o penúltimo caractere é "}"
            if(string.charAt(string.length() - 2) != '}') {
                throw new FileCannotBeReadException("Invalid json file");
            }
        }

        // Valida se os campos obrigatórios estão presentes

        // Verifica se a propriedade "id" existe
        if(!string.contains("\"id\"")) {
            throw new FileCannotBeReadException("Field id is not present");
        }

        // Verifica se a propriedade "description" existe
        if(!string.contains("\"description\"")) {
            throw new FileCannotBeReadException("Field description is not present");
        }

        // Verifica se a propriedade "status" existe
        if(!string.contains("\"status\"")) {
            throw new FileCannotBeReadException("Field status is not present");
        }

        // Verifica se a propriedade "createdAt" existe
        if(!string.contains("\"createdAt\"")) {
            throw new FileCannotBeReadException("Filed createdAt is not present");
        }

        // Verifica se a propriedade "updatedAt" existe
        if(!string.contains("\"updatedAt\"")) {
            throw new FileCannotBeReadException("Field updatedAt is not present");
        }
    }

}
