package main.services;

import java.util.Properties;

public interface DatabaseService {
    void configureDatabase(String databaseName, String databasePath);
    Properties getDatabaseProperties();
}
