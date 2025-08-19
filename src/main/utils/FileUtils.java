package main.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import main.exceptions.FileCannotBeCreatedException;
import main.exceptions.FileCannotBeReadException;
import main.exceptions.FileCannotBeWrittenException;
import main.mapper.TaskMapper;
import main.model.entities.Task;

public class FileUtils {

    private final Path DATABASE_PATH;
    private final String DATABASE_NAME;
    private final Path DATABASE_COMPLETE_PATH;

    public FileUtils() {
        this.DATABASE_PATH = Path.of("src/resources/database");
        this.DATABASE_NAME = "database.json";
        this.DATABASE_COMPLETE_PATH = Path.of(DATABASE_PATH + "/" + DATABASE_NAME);
    }

    public FileUtils(String DATABASE_PATH, String DATABASE_NAME) {
        this.DATABASE_PATH = Path.of(DATABASE_PATH);
        this.DATABASE_NAME = DATABASE_NAME;
        this.DATABASE_COMPLETE_PATH = Path.of(DATABASE_PATH + "/" + DATABASE_NAME);
    }
    
    public String readDatabaseFile() {
        String string = "";
        try {
            List<String> lines = Files.readAllLines(DATABASE_COMPLETE_PATH);
            if(!lines.isEmpty()) {
                for(String line : lines) {
                    string += line.trim();
                }
            }
            if(!string.isBlank()) checkJsonFile(string);
        } catch(IOException e) {
            throw new FileCannotBeReadException();
        }
        return string;
    }

    public void createDirectoryAndDatabaseFile() {
        try {
            if(!Files.exists(DATABASE_PATH)) Files.createDirectories(DATABASE_PATH);
            if(!Files.exists(DATABASE_COMPLETE_PATH)) 
                Files.createFile(DATABASE_COMPLETE_PATH);
        } catch(IOException e) {
            throw new FileCannotBeCreatedException();
        }
    }

    public void writeDatabaseFile(Task task) {
        String json = TaskMapper.parseTaskToJson(task);
        String string = readDatabaseFile();
        if(!string.isBlank()) string = string.replace("]", ",").concat(json).concat("]");
        if(string.isBlank()) string = string.concat("[").concat(json).concat("]");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_COMPLETE_PATH.toString()))) {
            bw.write(string);
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }

    public void clearDatabaseFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_COMPLETE_PATH.toString()))) {
            bw.write("");
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }

    private void checkJsonFile(String string) {
        if(string.charAt(0) != '{' && string.charAt(0) != '[') {
            throw new FileCannotBeReadException("Invalid json file");
        }
        if(string.charAt(0) == '{' ) {
            if(string.charAt(string.length() - 1) != '}') {
                throw new FileCannotBeReadException("Invalid json file");
            }
        }
        if(string.charAt(0) == '[') {
            if(string.charAt(string.length() - 1) != ']') {
                throw new FileCannotBeReadException("Invalid json file");
            }
            if(string.charAt(1) != '{') {
                throw new FileCannotBeReadException("Invalid json file");
            }
            if(string.charAt(string.length() - 2) != '}') {
                throw new FileCannotBeReadException("Invalid json file");
            }
        }
        if(!string.contains("\"id\"")) {
            throw new FileCannotBeReadException("Field id is not present");
        }
        if(!string.contains("\"description\"")) {
            throw new FileCannotBeReadException("Field description is not present");
        }
        if(!string.contains("\"status\"")) {
            throw new FileCannotBeReadException("Field status is not present");
        }
        if(!string.contains("\"createdAt\"")) {
            throw new FileCannotBeReadException("Filed createdAt is not present");
        }
        if(!string.contains("\"updatedAt\"")) {
            throw new FileCannotBeReadException("Field updatedAt is not present");
        }
    }

}
