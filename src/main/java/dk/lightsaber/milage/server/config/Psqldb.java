package dk.lightsaber.milage.server.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("psqldb")
public class Psqldb {
    DbConfig config;

    public Psqldb(DbConfig config) {
        if (config != null) {
            this.config = config;
        } else {
            throw new IllegalArgumentException("No DB config defined, this must be done with a DB file or environment variables");
        }
    }

    @Bean
    public DataSource psqldbDatasource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        String jdbcUrl = "jdbc:postgresql://" + config.getJdbcHost() + ":" + config.getJdbcPort() + "/" +
            config.getJdbcDatabaseName();
        ds.setUrl(jdbcUrl);
        ds.setUser(config.getJdbcUser());
        ds.setPassword(config.getJdbcPass());
        return ds;
    }
}
