package main.services;

import java.nio.file.Path;
import java.util.Properties;

public interface ConfigurationService {
    public String getDatabaseName();
    public Path getDatabasePath();
    public Path getDatabaseCompletePath();
    public Properties getDatabaseProperties();
    public void updateDatabaseConfig(Properties properties);
}
