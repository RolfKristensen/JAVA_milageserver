package dk.lightsaber.milage.server.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Profile("psqldb")
@EnableJpaRepositories(
        basePackages = {"dk.lightsaber.milage.server.pdo.repository"}
)
@EnableTransactionManagement
public class Psqldb {
    private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());
    private final DbConfig config;
    private final ApplicationProperties properties;

    public Psqldb(ApplicationProperties properties) {
        this.properties = properties;
        this.config = dbConfig();
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

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("dk.lightsaber.milage.server.pdo.model");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    private DbConfig dbConfig() {
        if (properties.getDbPropertyFilePath() != null) {
            LOGGER.info("Using DB properties file: " + properties.getDbPropertyFilePath());
            return new DbPropertiesFileConfig(properties.getDbPropertyFilePath());
        } else {
            LOGGER.info("No DB properties file defined");
            // TODO: add support for ENV config
            return null;
        }
    }
}
