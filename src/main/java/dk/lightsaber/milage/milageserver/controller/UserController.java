package dk.lightsaber.milage.milageserver.controller;

import dk.lightsaber.milage.server.pdo.model.UserPdo;
import dk.lightsaber.milage.server.service.IUserService;
import dk.lightsaber.milage.server.service.dto.model.AuthToken;
import dk.lightsaber.milage.server.service.dto.model.CarDto;
import dk.lightsaber.milage.server.service.dto.model.UserCredentialsDto;
import dk.lightsaber.milage.server.service.dto.model.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("users")
public class UserController {
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    private IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @RequestMapping(
        path = "/users",
        method = RequestMethod.GET
    )
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(service.getAllRecords());
    }

    @RequestMapping(
        path = "/users",
        method = RequestMethod.POST
    )
    public ResponseEntity<String> create(
        @RequestBody UserDto user
    ) {
        try {
            Long id = service.create(user);
            return ResponseEntity
                .accepted()
                .body(id.toString());
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
        }
    }

    @RequestMapping(
        path = "/authtoken/createToken",
        method = RequestMethod.POST
    )
    public ResponseEntity<String> createAccessToken(
        @RequestBody UserCredentialsDto credentialsDto
    ) {
        String token = null;
        try {
            token = service.createUserAuthToken(credentialsDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
        }
        return ResponseEntity.ok(token);
    }

    @RequestMapping(
        path = "/authtoken/users",
        method = RequestMethod.GET
    )
    public ResponseEntity<UserDto> getUserInfo(
        @RequestHeader(name = AUTH_TOKEN) String authToken
    ) {
        UserDto user;
        try {
            user = service.getUserInfo(new AuthToken(authToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .notFound()
                .build();
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(
        path = "/authtoken/cars",
        method = RequestMethod.GET
    )
    public ResponseEntity<List<CarDto>> getUserCars(
        @RequestHeader(name = AUTH_TOKEN) String authToken
    ) {
        List<CarDto> cars;
        try {
            cars = service.getListOfCars(new AuthToken(authToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .notFound()
                .build();
        }
        return ResponseEntity.ok(cars);
    }

    @RequestMapping(
        path = "/passwordtoken/change",
        method = RequestMethod.POST,
        consumes = MediaType.TEXT_PLAIN_VALUE,
        produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> changePassword(
        @RequestHeader(name = AUTH_TOKEN) String authToken,
        @RequestBody String newPassword
    ){
        try {
            service.changePassword(new AuthToken(authToken), newPassword);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
