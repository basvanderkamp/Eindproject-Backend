package nl.novi.homeprojects.controllers;

import nl.novi.homeprojects.dtos.input.UserInputDto;
import nl.novi.homeprojects.dtos.output.UserOutputDto;
import nl.novi.homeprojects.exceptions.BadRequestException;
import nl.novi.homeprojects.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserOutputDto>> getUsers() {

        List<UserOutputDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable("username") String username) {

        UserOutputDto optionalUser = userService.getUser(username);

        return ResponseEntity.ok().body(optionalUser);
    }


    @PostMapping(value = "")
    public ResponseEntity<UserOutputDto> createUser(@RequestBody UserInputDto inputDto) {;

        String newUsername = userService.createUser(inputDto);
        userService.addAuthority(newUsername, "ROLE_USER");
        userService.addAuthority(newUsername, "ROLE_EXECUTOR");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable("username") String username, @RequestBody UserOutputDto dto) {

        userService.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }


    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }


    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/clients/{clientId}")
    public void assignClientToUser(@PathVariable String id, @PathVariable String clientId) {
        UserService.assignClientToUser(id, clientId);
    }
}