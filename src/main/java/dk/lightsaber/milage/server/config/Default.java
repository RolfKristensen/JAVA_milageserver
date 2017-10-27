package dk.lightsaber.milage.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"dk.lightsaber.milage.server.pdo.repository"}
)
@EnableTransactionManagement
public class Default {
    private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());

    private final ApplicationProperties props;

    public Default(ApplicationProperties props) {
        this.props = props;
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

    @Bean
    public DbConfig dbConfig() {
        if (props.getDbPropertyFilePath() != null) {
            LOGGER.info("Using DB properties file: " + props.getDbPropertyFilePath());
            return new DbPropertiesFileConfig(props.getDbPropertyFilePath());
        } else {
            LOGGER.info("No DB properties file defined");
            // TODO: add support for ENV config
            return null;
        }
    }
}
