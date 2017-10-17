package dk.lightsaber.milage.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPass;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPass() {
        return jdbcPass;
    }

    public void setJdbcPass(String jdbcPass) {
        this.jdbcPass = jdbcPass;
    }
}
