package dk.lightsaber.milage.milageserver;

import dk.lightsaber.milage.server.config.*;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@EnableConfigurationProperties
@Import({Psqldb.class, Hsqldb.class, ApplicationProperties.class, Controllers.class, Default.class})
public class MilageserverApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MilageserverApplication.class, args);
		context.getBean(Logger.class).info("Application started");
	}
}
