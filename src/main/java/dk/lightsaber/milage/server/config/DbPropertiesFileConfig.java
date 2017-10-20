package dk.lightsaber.milage.server.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class DbPropertiesFileConfig implements DbConfig {
    private Properties properties;

    public DbPropertiesFileConfig(Path filePath) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath.toFile()));
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not open database properties file: " +  filePath.toString());
        }
    }

    @Override
    public String getJdbcHost() {
        return getPropertyValue("jdbc_host");
    }

    @Override
    public String getJdbcPort() {
        return getPropertyValue("jdbc_port");
    }

    @Override
    public String getJdbcDatabaseName() {
        return getPropertyValue("jdbc_db_name");
    }

    @Override
    public String getJdbcUser() {
        return getPropertyValue("jdbc_user");
    }

    @Override
    public String getJdbcPass() {
        return getPropertyValue("jdbc_pass");
    }

    private String getPropertyValue(String key) {
        String value = properties.getProperty(key);
        if (value != null && !value.isEmpty()) {
            return value;
        } else {
            throw new IllegalArgumentException("DB properties file must contain the property: " + key);
        }
    }
}
