package dk.lightsaber.milage.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String appName;
    private Path dbPropertyFilePath;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Path getDbPropertyFilePath() {
        return dbPropertyFilePath;
    }

    public void setDbPropertyFilePath(Path dbPropertyFilePath) {
        this.dbPropertyFilePath = dbPropertyFilePath;
    }
}
