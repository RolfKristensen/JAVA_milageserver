package dk.lightsaber.milage.server.config;

import dk.lightsaber.milage.milageserver.controller.UserController;
import dk.lightsaber.milage.server.pdo.repository.IUserJpaRepository;
import dk.lightsaber.milage.server.service.IUserService;
import dk.lightsaber.milage.server.service.UserServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Controllers {
    @Autowired
    IUserJpaRepository userRepository;

    @Bean
    public UserController milageController(IUserService service) {
        return new UserController(service);
    }

    @Bean
    public IUserService userService() {
        return new UserServiceImpl(userRepository);
    }
}
