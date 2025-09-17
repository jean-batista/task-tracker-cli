package main.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import main.database.utils.JsonFileUtils;
import main.exceptions.FileCannotBeCreatedException;
import main.exceptions.FileCannotBeReadException;
import main.exceptions.FileCannotBeWrittenException;
import main.mapper.TaskMapper;
import main.model.entities.Task;
import main.services.ConfigurationService;

// Classe responsável pelas operações do arquivo de dados
public class DatabaseManager {

    private ConfigurationService service;

    public DatabaseManager(ConfigurationService service) {
        this.service = service;
    }
    
    // Faz a leitura do arquivo de dados
    public String readDatabaseFile() {
        String string = "";
        try {
            List<String> lines = Files.readAllLines(service.getDatabaseCompletePath());
            if(!lines.isEmpty()) {
                for(String line : lines) {
                    string += line.trim();
                }
            }
            if(!string.isBlank()) JsonFileUtils.checkJsonFile(string);
        } catch(IOException e) {
            throw new FileCannotBeReadException();
        }
        return string;
    }

    // Cria os diretórios e o arquivo de dados
    public void createDirectoryAndDatabaseFile() {
        try {
            if(!Files.exists(service.getDatabasePath())) Files.createDirectories(service.getDatabasePath());
            if(!Files.exists(service.getDatabaseCompletePath())) 
                Files.createFile(service.getDatabaseCompletePath());
        } catch(IOException e) {
            throw new FileCannotBeCreatedException();
        }
    }

    // Escreve uma tarefa no arquivo de dados
    public void writeDatabaseFile(Task task) {
        String json = TaskMapper.parseTaskToJson(task);
        String string = readDatabaseFile();
        if(!string.isBlank()) string = string.replace("]", ",").concat(json).concat("]");
        if(string.isBlank()) string = string.concat("[").concat(json).concat("]");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(service.getDatabaseCompletePath().toString()))) {
            bw.write(string);
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }

    // Limpa o arquivo de dados
    public void clearDatabaseFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(service.getDatabaseCompletePath().toString()))) {
            bw.write("");
        } catch(IOException e) {
            throw new FileCannotBeWrittenException();
        }
    }

}
