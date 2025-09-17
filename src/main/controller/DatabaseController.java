package main.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import main.services.DatabaseService;

public class DatabaseController {
    
    private DatabaseService service;

    public DatabaseController(DatabaseService service) {
        this.service = service;
    }

    public void configureDatabase(String databaseName, String databasePath) {
        service.configureDatabase(databaseName, databasePath);
    }

    public Map<String, String> getDatabaseProperties() {
        Properties properties = service.getDatabaseProperties();
        Map<String, String> map = new HashMap<>();
        map.put("database-path: ", properties.getProperty("database-path"));
        map.put("database-name: ", properties.getProperty("database-name"));
        map.put("database-complete-path: ", properties.getProperty("database-complete-path"));
        return map;
    }

}
