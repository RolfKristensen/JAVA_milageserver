package dk.lightsaber.milage.server.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("psqldb")
public class Psqldb {
    private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());
    private final DbConfig config;

    public Psqldb(DbConfig dbConfig) {
        this.config = dbConfig;
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
