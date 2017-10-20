package dk.lightsaber.milage.server.config;

public interface DbConfig {
    String getJdbcHost();
    String getJdbcPort();
    String getJdbcDatabaseName();
    String getJdbcUser();
    String getJdbcPass();
}
