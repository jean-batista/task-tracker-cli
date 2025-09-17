package main.database;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import main.database.utils.DatabasePropertiesUtils;
import main.exceptions.DatabasePropertiesFileCannotBeReadException;
import main.exceptions.DatabasePropertiesFileCannotBeWrittenException;
import main.exceptions.InvalidFileNameException;
import main.exceptions.InvalidFilePathException;

// Classe responsável pelas pelas operações do arquivo de configurações
public class DatabaseProperties {

    private Path DATABASE_PROPERTIES_PATH;
    private Path DATABASE_PROPERTIES_COMPLETE_PATH;

    // Construtor com configurações padrões
    public DatabaseProperties() {
        this.DATABASE_PROPERTIES_PATH = Path.of("src/resources/database");
        this.DATABASE_PROPERTIES_COMPLETE_PATH = Path.of("src/resources/database/database.properties");
        autoConfigure();
    }
    
    // Construtor com configurações personalizadas
    public DatabaseProperties(Path databasePropertiesPath, Path databasePropertiesCompletePath) {
        this.DATABASE_PROPERTIES_PATH = databasePropertiesPath;
        this.DATABASE_PROPERTIES_COMPLETE_PATH = databasePropertiesCompletePath;
        autoConfigure();
    }

    public String getDatabaseName() {
        Properties properties = readDatabaseProperties();
        return properties.getProperty("database-name");
    }
    
    public Path getDatabasePath() {
        Properties properties = readDatabaseProperties();
        return Path.of(properties.getProperty("database-path"));
    }

    public Path getDatabaseCompletePath() {
        Properties properties = readDatabaseProperties();
        return Path.of(properties.getProperty("database-complete-path"));
    }

    // Faz a leitura das configurações do arquivo de configurações
    public Properties readDatabaseProperties() {
        try(FileInputStream stream = new FileInputStream(DATABASE_PROPERTIES_COMPLETE_PATH.toString())) {
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch(Exception e) {
            throw new DatabasePropertiesFileCannotBeReadException("Não foi possível ler o arquivo de propriedades do banco de dados");
        }
    }

    // Atualiza as configurações do arquivo de dados
    public void updateDatabaseConfig(Properties properties) {
        String path = properties.getProperty("database-path");
        String name = properties.getProperty("database-name");
        boolean isValidPath = DatabasePropertiesUtils.isValidPath(path);
        boolean isValidName = DatabasePropertiesUtils.isValidName(name);
        if(!isValidPath) throw new InvalidFilePathException("O caminho fornecido é inválido");
        if(!isValidName) throw new InvalidFileNameException("O nome do arquivo é inválido");
        writeDatabaseProperties(properties);
    }

    // Cria os diretórios e o arquivo de configuração do arquivo de dados
    private void createDirectoryAndDatabasePropertiesFile() {
        try {
            Files.createDirectories(DATABASE_PROPERTIES_PATH);
            Files.createFile(DATABASE_PROPERTIES_COMPLETE_PATH);
        } catch(Exception e) {
            throw new DatabasePropertiesFileCannotBeReadException("Não foi possível ler o arquivo de propriedades do banco de dados");
        }
    }

    // Escreve as configurações no arquivo de configurações
    private void writeDatabaseProperties(Properties properties) {
        try(BufferedWriter writer = Files.newBufferedWriter(DATABASE_PROPERTIES_COMPLETE_PATH)) {
            properties.store(writer, "Configurações do banco de dados");
        } catch(IOException e) {
            throw new DatabasePropertiesFileCannotBeWrittenException("Não foi possível escrever no arquivo de propriedades do banco de dados");
        }
    }

    // Faz a autoconfiguração da aplicação
    // É usado principalmente quando a aplicação é iniciada pela primeira vez
    private void autoConfigure() {

        // Configura o arquivo de configurações da aplicação
        if(DATABASE_PROPERTIES_COMPLETE_PATH.endsWith("database.properties")) {
            if(!DatabasePropertiesUtils.verifyIfDatabasePropertiesExists(DATABASE_PROPERTIES_COMPLETE_PATH)) {
                createDirectoryAndDatabasePropertiesFile();
                var properties = DatabasePropertiesUtils.createDatabaseProperties();
                writeDatabaseProperties(properties);
            }
            if(DatabasePropertiesUtils.verifyIfDatabasePropertiesIsEmpty(DATABASE_PROPERTIES_COMPLETE_PATH)) {
                var properties = DatabasePropertiesUtils.createDatabaseProperties();
                writeDatabaseProperties(properties);
            }
        }
        
        // Configura o arquivo de configurações dos testes
        if(DATABASE_PROPERTIES_COMPLETE_PATH.endsWith("database-test.properties")) {
            if(!DatabasePropertiesUtils.verifyIfDatabaseTestPropertiesExists(DATABASE_PROPERTIES_COMPLETE_PATH)) {
                createDirectoryAndDatabasePropertiesFile();
                var properties = DatabasePropertiesUtils.createDatabaseTestProperties();
                writeDatabaseProperties(properties);
            }
            if(DatabasePropertiesUtils.verifyIfDatabaseTestPropertiesIsEmpty(DATABASE_PROPERTIES_COMPLETE_PATH)) {
                var properties = DatabasePropertiesUtils.createDatabaseTestProperties();
                writeDatabaseProperties(properties);
            }
        }
        
    }

}
