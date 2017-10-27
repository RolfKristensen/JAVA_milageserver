package dk.lightsaber.milage.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Default {
    private final static Logger LOGGER = LoggerFactory.getLogger(Default.class.getName());

    private final ApplicationProperties props;

    public Default(ApplicationProperties props) {
        this.props = props;
    }
}
