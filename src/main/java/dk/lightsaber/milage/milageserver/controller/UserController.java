package dk.lightsaber.milage.milageserver.controller;

import dk.lightsaber.milage.server.service.IUserService;
import dk.lightsaber.milage.server.service.dto.model.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("user")
public class UserController {
    private IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @RequestMapping(
        path = "/user",
        method = RequestMethod.GET
    )
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(service.getAllRecords());
    }

    @RequestMapping(
        path = "/user",
        method = RequestMethod.POST
    )
    public ResponseEntity<String> create(@RequestBody UserDto user) {
        try {
            Long id = service.create(user);
            return ResponseEntity.accepted().body(id.toString());
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body(e.getMessage());
        }
    }
}
