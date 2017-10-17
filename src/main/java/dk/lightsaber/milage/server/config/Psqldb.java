package dk.lightsaber.milage.server.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("psqldb")
public class Psqldb {
    ApplicationProperties properties;

    public Psqldb(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource psqldbDatasource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(properties.getJdbcUrl());
        ds.setUser(properties.getJdbcUser());
        ds.setPassword(properties.getJdbcPass());
        return ds;
    }
}
