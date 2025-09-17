package main.services.impl;

import java.util.Properties;

import main.services.ConfigurationService;
import main.services.DatabaseService;

// Classe de serviço responsável pelas configurações
public class DatabaseServiceImpl implements DatabaseService {

    private ConfigurationService service;

    public DatabaseServiceImpl(ConfigurationService service) {
        this.service = service;
    }

    // Configura o arquivo de dados
    @Override
    public void configureDatabase(String databaseName, String databasePath) {
        if(databaseName.isBlank()) databaseName = service.getDatabaseName().replace(".json", "");
        if(databasePath.isBlank()) databasePath = service.getDatabasePath().toString();
        String name = checkDatabaseName(databaseName);
        String path = checkDatabasePath(databasePath);
        Properties properties = new Properties();
        properties.setProperty("database-name", name + ".json");
        properties.setProperty("database-path", path);
        properties.setProperty("database-complete-path", path + "/" + name + ".json");
        service.updateDatabaseConfig(properties);
    }

    @Override
    public Properties getDatabaseProperties() {
        return service.getDatabaseProperties();
    }

    // Verifica se o databaseName passado é válido
    private String checkDatabaseName(String databaseName) {
        if(databaseName.endsWith("/")) databaseName = databaseName.substring(0, databaseName.length() - 1);
        if(databaseName.startsWith("/")) databaseName = databaseName.substring(1, databaseName.length());
        while(databaseName.indexOf(".") != -1) {
            databaseName = databaseName.substring(0, databaseName.indexOf("."));
        }
        return databaseName;
    }

    // Verifica se o databasePath passado é válido
    private String checkDatabasePath(String databasePath) {
        String path = databasePath.replace("\\", "/");
        if(path.endsWith("/")) path = path.substring(0, path.length() - 1);
        if(path.startsWith("/")) path = path.substring(1, path.length());
        return path;
    }
    
}
