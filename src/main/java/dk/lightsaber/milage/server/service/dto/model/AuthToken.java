package dk.lightsaber.milage.server.service.dto.model;

import java.util.UUID;

public class AuthToken {
    private final String token;

    public AuthToken(UUID token) {
        this.token = token.toString();
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public AuthToken() {
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }
}
