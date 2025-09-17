package test.main.config;

import java.nio.file.Path;

public interface Paths {
    
    public final Path PATH = Path.of("src/test/resources/database");
    public final Path COMPLETE_PATH = Path.of("src/test/resources/database/database-test.properties");
    public final Path DATABASE_COMPLETE_PATH = Path.of("src/test/resources/database/database-test.json");

}
