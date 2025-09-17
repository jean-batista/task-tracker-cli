package main.services.impl;

import java.nio.file.Path;
import java.util.Properties;

import main.database.DatabaseProperties;
import main.services.ConfigurationService;

public class ConfigurationServiceImpl implements ConfigurationService {

    private DatabaseProperties properties;

    public ConfigurationServiceImpl(DatabaseProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getDatabaseName() {
        return properties.getDatabaseName();
    }

    @Override
    public Path getDatabasePath() {
        return properties.getDatabasePath();
    }

    @Override
    public Path getDatabaseCompletePath() {
        return properties.getDatabaseCompletePath();
    }

    @Override
    public Properties getDatabaseProperties() {
        return properties.readDatabaseProperties();
    }

    @Override
    public void updateDatabaseConfig(Properties properties) {
        this.properties.updateDatabaseConfig(properties);
    }
    
}
