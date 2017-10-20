package dk.lightsaber.milage.server.config;

public class DbEnvPropertiesConfig implements DbConfig {
    private String jdbcHost;
    private String jdbcPort;
    private String jdbcDbName;
    private String jdbcUser;
    private String jdbcPass;

    @Override
    public String getJdbcHost() {
        return jdbcHost;
    }

    @Override
    public String getJdbcPort() {
        return jdbcPort;
    }

    @Override
    public String getJdbcDatabaseName() {
        return jdbcDbName;
    }

    @Override
    public String getJdbcUser() {
        return jdbcUser;
    }

    @Override
    public String getJdbcPass() {
        return jdbcPass;
    }

    public void setJdbcHost(String jdbcHost) {
        this.jdbcHost = jdbcHost;
    }

    public void setJdbcPort(String jdbcPort) {
        this.jdbcPort = jdbcPort;
    }

    public void setJdbcDbName(String jdbcDbName) {
        this.jdbcDbName = jdbcDbName;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public void setJdbcPass(String jdbcPass) {
        this.jdbcPass = jdbcPass;
    }
}
